<!--
  - Copyright 2014-2019 the original author or authors.
  -
  - Licensed under the Apache License, Version 2.0 (the "License");
  - you may not use this file except in compliance with the License.
  - You may obtain a copy of the License at
  -
  -     http://www.apache.org/licenses/LICENSE-2.0
  -
  - Unless required by applicable law or agreed to in writing, software
  - distributed under the License is distributed on an "AS IS" BASIS,
  - WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
  - See the License for the specific language governing permissions and
  - limitations under the License.
  -->

<template>
    <div class="section">
        <div class="container">
            <h1 class="title" v-text="$t('log.title')"/>
            <h2
                    v-if="filter.application"
                    v-text="filter.application"
                    class="subtitle"
            />
            <h1
                    v-else-if="filter.instanceId"
                    v-text="`${getName(filter.instanceId)} (${filter.instanceId})`"
                    class="subtitle"
            />
            <table class="journal table is-fullwidth is-hoverable">
                <thead>
                <tr>
                    <th v-text="$t('log.time')"/>
                    <th v-text="$t('log.serviceId')"/>
                    <th v-text="$t('log.traceId')"/>
                    <th v-text="$t('log.uri')"/>
                    <th v-text="$t('log.status')"/>
                    <th v-text="$t('log.method')"/>
                    <th v-text="$t('log.consumeTime')"/>
                </tr>
                </thead>
                <transition-group tag="tbody" name="fade-in">
                    <tr key="new-logs" v-if="newlogsCount > 0">
                        <td
                                colspan="6"
                                class="has-text-primary has-text-centered is-selectable"
                                v-text="`${newlogsCount} new logs`"
                                @click="showNewlogs"
                        />
                    </tr>
                    <template v-for="log in listedlogs" :key="log.traceId">
                        <tr class="is-selectable" :key="log.key"
                            @click="showPayload[log.key] ? $delete(showPayload, log.key) : $set(showPayload, log.key, true)">
                            <td v-text="format(new Date(log.createTime),'yyyy-MM-dd hh:mm:ss')"/>
                            <td v-text="log.serviceId"/>
                            <td v-text="log.traceId"/>
                            <td v-text="log.requestUri"/>
                            <td v-text="log.httpStatus"/>
                            <td v-text="log.requestMethod"/>
                            <td v-text="log.timeConsuming"/>
                        </tr>
                        <tr :key="`${log.key}-detail`" v-if="showPayload[log.key]">
                            <td colspan="7">
                                <pre class="is-breakable" v-text="toJson(log.payload)"/>
                            </td>
                        </tr>
                    </template>
                </transition-group>
            </table>
        </div>
    </div>
</template>

<script>
    import subscribing from '@/mixins/subscribing';
    import Instance from '@/services/instance';
    import {compareBy} from '@/utils/collections';
    import isEqual from 'lodash/isEqual';
    import uniq from 'lodash/uniq';

    class Log {
        constructor({serviceId, createTime, traceId, requestUri, requestMethod, timeConsuming, httpStatus, ...payload}) {
            this.serviceId = serviceId;
            this.createTime = createTime;
            this.traceId = traceId;
            this.requestUri = requestUri;
            this.requestMethod = requestMethod;
            this.httpStatus = httpStatus;
            this.timeConsuming = timeConsuming;
            this.payload = payload;
        }

        get key() {
            return `${this.traceId}`;
        }
    }

    export default {
        mixins: [subscribing],
        data: () => ({
            logs: [],
            listOffset: 0,
            showPayload: {},
            error: null,
            filter: {
                application: undefined,
                instanceId: undefined
            }
        }),
        computed: {
            instanceNames() {
                return this.logs.filter(event => event.type === 'REGISTERED').reduce((names, event) => {
                    names[event.instance] = event.payload.registration.name;
                    return names;
                }, {});
            },
            listedlogs() {
                return this.filterlogs(this.logs.slice(this.listOffset));
            },
            newlogsCount() {
                return this.filterlogs(this.logs.slice(0, this.listOffset)).length;
            }
        },
        methods: {
            toJson(obj) {
                return JSON.stringify(obj, null, 4);
            },
            getName(instanceId) {
                return this.instanceNames[instanceId] || '?'
            },
            getInstances(application) {
                return uniq(Object.entries(this.instanceNames)
                    .filter(([, name]) => application === name)
                    .map(([instanceId]) => instanceId));
            },
            showNewlogs() {
                this.listOffset = 0;
            },
            filterlogs(logs) {
                return logs;
            },
            createSubscription() {
                /*return Instance.getLogStream().subscribe({
                    next: message => {
                        this.error = null;
                        this.logs = Object.freeze([new Event(message.data), ...this.logs]);
                        this.listOffset += 1;
                    },
                    error: error => {
                        console.warn('Listening for logs failed:', error);
                        this.error = error;
                    }
                });*/
            }, format(date, fmt) {
                let o = {
                    'M+': date.getMonth() + 1, // 月份
                    'd+': date.getDate(), // 日
                    'h+': date.getHours(), // 小时
                    'm+': date.getMinutes(), // 分
                    's+': date.getSeconds(), // 秒
                    'S': date.getMilliseconds() // 毫秒
                }
                if (/(y+)/.test(fmt)) {
                    fmt = fmt.replace(RegExp.$1, (date.getFullYear() + '').substr(4 - RegExp.$1.length))
                }
                for (var k in o) {
                    if (new RegExp('(' + k + ')').test(fmt)) {
                        fmt = fmt.replace(RegExp.$1, (RegExp.$1.length === 1) ? (o[k]) : (('00' + o[k]).substr(('' + o[k]).length)))
                    }
                }
                return fmt
            }
        },
        watch: {
            '$route.query': {
                immediate: true,
                handler() {
                    this.filter = this.$route.query
                }
            },
            filter: {
                deep: true,
                immediate: true,
                handler() {
                    if (!isEqual(this.filter, this.$route.query)) {
                        this.$router.replace({
                            name: 'logs',
                            query: this.filter
                        });
                    }
                }
            }
        },
        async created() {
            try {
                // get request logs
                const response = await Instance.fetchLogs();
                const logs = response.data.sort(compareBy(v => v.createTime)).reverse().map(e => new Log(e));
                this.logs = Object.freeze(logs);
                this.error = null;
            } catch (error) {
                console.warn('Fetching logs failed:', error);
                this.error = error;
            }
        },
        install({viewRegistry}) {
            viewRegistry.addView({
                path: '/logs',
                name: 'logs',
                label: 'log.top-menu',
                order: 100,
                component: this
            });
            viewRegistry.addRedirect('/', 'logs');
        }
    };
</script>
