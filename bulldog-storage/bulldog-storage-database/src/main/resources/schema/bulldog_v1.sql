-- MySQL dump 10.13  Distrib 8.0.18, for osx10.15 (x86_64)
--
-- Host: 127.0.0.1    Database: bulldog
-- ------------------------------------------------------
-- Server version	8.0.21

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `bulldog_global_logs`
--

DROP TABLE IF EXISTS `bulldog_global_logs`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bulldog_global_logs` (
  `global_log_id` varchar(36) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '全局日志主键',
  `log_id` varchar(36) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '日志编号，关联bulldog_request_logs、bulldog_non_request_logs主键',
  `log_level` varchar(10) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '日志等级',
  `message` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '消息内容',
  `call_class_name` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '日志所产生的调用类名',
  `call_method_name` varchar(30) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '日志所产生的调用方法名',
  `call_file_name` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '日志所产生的调用文件名',
  `call_line_number` int DEFAULT NULL COMMENT '日志所产生的调用行号',
  `exception_stack` text COLLATE utf8mb4_unicode_ci COMMENT 'ERROR等级日志的堆栈信息',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '产生时间',
  PRIMARY KEY (`global_log_id`),
  UNIQUE KEY `bulldog_global_logs_global_log_id_uindex` (`global_log_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='全局日志信息表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `bulldog_non_request_logs`
--

DROP TABLE IF EXISTS `bulldog_non_request_logs`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bulldog_non_request_logs` (
  `log_id` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '日志编号',
  `trace_id` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '链路日志编号',
  `span_id` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '单元编号',
  `parent_span_id` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '上级单元编号',
  `service_id` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '服务编号，关联bulldog_service_instance主键',
  `start_time` mediumtext CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci COMMENT '请求开始时间，单位：毫秒',
  `end_time` mediumtext CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci COMMENT '请求结束时间，单位：毫秒',
  `time_consuming` mediumtext CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci COMMENT '请求耗时，单位：毫秒',
  `metadata` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '附加元数据',
  `group_name` varchar(20) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '分组名称',
  `tag` varchar(20) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '标签名称',
  PRIMARY KEY (`log_id`),
  UNIQUE KEY `bulldog_non_request_logs_log_id_uindex` (`log_id`),
  KEY `bulldog_non_request_logs_parent_span_id_index` (`parent_span_id`),
  KEY `bulldog_non_request_logs_service_id_index` (`service_id`),
  KEY `bulldog_non_request_logs_span_id_index` (`span_id`),
  KEY `bulldog_non_request_logs_trace_id_index` (`trace_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='非请求日志信息表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `bulldog_request_logs`
--

DROP TABLE IF EXISTS `bulldog_request_logs`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bulldog_request_logs` (
  `log_id` varchar(36) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '日志编号',
  `trace_id` varchar(36) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '链路日志编号',
  `span_id` varchar(36) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '单元编号',
  `parent_span_id` varchar(36) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '上级单元编号',
  `service_id` varchar(36) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '服务编号，关联bulldog_service_instance主键',
  `start_time` mediumtext COLLATE utf8mb4_unicode_ci COMMENT '请求开始时间，单位：毫秒',
  `end_time` mediumtext COLLATE utf8mb4_unicode_ci COMMENT '请求结束时间，单位：毫秒',
  `time_consuming` mediumtext COLLATE utf8mb4_unicode_ci COMMENT '请求耗时，单位：毫秒',
  `metadata` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '附加元数据',
  `request_uri` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '请求路径',
  `request_method` varchar(10) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '请求方法',
  `request_ip` varchar(20) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '客户端IPv4地址',
  `request_url_params` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '请求路径参数',
  `request_body_params` text COLLATE utf8mb4_unicode_ci COMMENT '请求主体参数',
  `request_headers` text COLLATE utf8mb4_unicode_ci COMMENT '请求头列表',
  `response_body` text COLLATE utf8mb4_unicode_ci COMMENT '响应主体内容',
  `response_status` varchar(10) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '响应状态',
  `response_headers` text COLLATE utf8mb4_unicode_ci COMMENT '响应头列表',
  `exception_stack` text COLLATE utf8mb4_unicode_ci COMMENT '异常堆栈信息',
  PRIMARY KEY (`log_id`),
  UNIQUE KEY `bulldog_request_logs_log_id_uindex` (`log_id`),
  KEY `bulldog_request_logs_parent_span_id_index` (`parent_span_id`),
  KEY `bulldog_request_logs_service_id_index` (`service_id`),
  KEY `bulldog_request_logs_span_id_index` (`span_id`),
  KEY `bulldog_request_logs_trace_id_index` (`trace_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='请求日志信息表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `bulldog_service_instance`
--

DROP TABLE IF EXISTS `bulldog_service_instance`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bulldog_service_instance` (
  `service_id` varchar(36) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `service_name` varchar(30) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `service_ip` varchar(20) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `service_port` int DEFAULT NULL,
  `service_hostname` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `last_report_time` datetime DEFAULT NULL,
  `create_time` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='服务实例详情信息表';
/*!40101 SET character_set_client = @saved_cs_client */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-09-29 16:26:08
