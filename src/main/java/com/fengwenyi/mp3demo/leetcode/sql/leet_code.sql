/*
 Navicat Premium Data Transfer

 Source Server         : localhost-本地
 Source Server Type    : MySQL
 Source Server Version : 50725
 Source Host           : localhost:3306
 Source Schema         : leet_code

 Target Server Type    : MySQL
 Target Server Version : 50725
 File Encoding         : 65001

 Date: 02/09/2019 14:17:40
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for address
-- ----------------------------
DROP TABLE IF EXISTS `address`;
CREATE TABLE `address`  (
  `AddressId` int(11) NOT NULL,
  `PersonId` int(11) NULL DEFAULT NULL,
  `City` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `State` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`AddressId`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of address
-- ----------------------------
INSERT INTO `address` VALUES (1, 1, '上海', '青浦');

-- ----------------------------
-- Table structure for customers
-- ----------------------------
DROP TABLE IF EXISTS `customers`;
CREATE TABLE `customers`  (
  `Id` int(11) NOT NULL,
  `Name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`Id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of customers
-- ----------------------------
INSERT INTO `customers` VALUES (1, 'Joe');
INSERT INTO `customers` VALUES (2, 'Henry');
INSERT INTO `customers` VALUES (3, 'Sam');
INSERT INTO `customers` VALUES (4, 'Max');

-- ----------------------------
-- Table structure for department
-- ----------------------------
DROP TABLE IF EXISTS `department`;
CREATE TABLE `department`  (
  `Id` int(11) NOT NULL,
  `Name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`Id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of department
-- ----------------------------
INSERT INTO `department` VALUES (1, 'IT');
INSERT INTO `department` VALUES (2, 'Sales');

-- ----------------------------
-- Table structure for employee
-- ----------------------------
DROP TABLE IF EXISTS `employee`;
CREATE TABLE `employee`  (
  `Id` bigint(20) NOT NULL,
  `Name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `Salary` bigint(255) NULL DEFAULT NULL,
  `ManagerId` bigint(20) NULL DEFAULT NULL,
  `DepartmentId` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`Id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of employee
-- ----------------------------
INSERT INTO `employee` VALUES (1, 'Joe', 70000, 3, 1);
INSERT INTO `employee` VALUES (2, 'Henry', 80000, 4, 2);
INSERT INTO `employee` VALUES (3, 'Sam', 60000, NULL, 2);
INSERT INTO `employee` VALUES (4, 'Max', 90000, NULL, 1);
INSERT INTO `employee` VALUES (5, 'Janet', 69000, NULL, 1);
INSERT INTO `employee` VALUES (6, 'Randy', 85000, NULL, 1);
INSERT INTO `employee` VALUES (7, 'JoeQueue', 90000, NULL, 1);

-- ----------------------------
-- Table structure for logs
-- ----------------------------
DROP TABLE IF EXISTS `logs`;
CREATE TABLE `logs`  (
  `Id` int(11) NOT NULL,
  `Num` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`Id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of logs
-- ----------------------------
INSERT INTO `logs` VALUES (1, 1);
INSERT INTO `logs` VALUES (2, 1);
INSERT INTO `logs` VALUES (3, 1);
INSERT INTO `logs` VALUES (4, 2);
INSERT INTO `logs` VALUES (5, 1);
INSERT INTO `logs` VALUES (6, 2);
INSERT INTO `logs` VALUES (7, 2);

-- ----------------------------
-- Table structure for orders
-- ----------------------------
DROP TABLE IF EXISTS `orders`;
CREATE TABLE `orders`  (
  `Id` int(11) NOT NULL,
  `CustomerId` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`Id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of orders
-- ----------------------------
INSERT INTO `orders` VALUES (1, 3);
INSERT INTO `orders` VALUES (2, 1);

-- ----------------------------
-- Table structure for person
-- ----------------------------
DROP TABLE IF EXISTS `person`;
CREATE TABLE `person`  (
  `PersonId` int(11) NOT NULL,
  `FirstName` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `LastName` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `Email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`PersonId`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of person
-- ----------------------------
INSERT INTO `person` VALUES (1, '鑫', '蔡', 'a@b.com');
INSERT INTO `person` VALUES (2, '彦祖', '吴', 'c@d.com');
INSERT INTO `person` VALUES (4, '嗯', '呃', 'john@example.com');
INSERT INTO `person` VALUES (5, '嗯', '呃', 'bob@example.com');

-- ----------------------------
-- Table structure for scores
-- ----------------------------
DROP TABLE IF EXISTS `scores`;
CREATE TABLE `scores`  (
  `Id` int(11) NOT NULL,
  `Score` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`Id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of scores
-- ----------------------------
INSERT INTO `scores` VALUES (1, '3.50');
INSERT INTO `scores` VALUES (2, '3.65');
INSERT INTO `scores` VALUES (3, '4.00');
INSERT INTO `scores` VALUES (4, '3.85');
INSERT INTO `scores` VALUES (5, '4.00');
INSERT INTO `scores` VALUES (6, '3.65');

-- ----------------------------
-- Table structure for trips
-- ----------------------------
DROP TABLE IF EXISTS `trips`;
CREATE TABLE `trips`  (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Client_Id` int(11) NULL DEFAULT NULL,
  `Driver_Id` int(11) NULL DEFAULT NULL,
  `City_Id` int(11) NULL DEFAULT NULL,
  `Status` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `Request_at` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`Id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of trips
-- ----------------------------
INSERT INTO `trips` VALUES (1, 1, 10, 1, '    completed      ', '2013-10-01 00:00:00');
INSERT INTO `trips` VALUES (2, 2, 11, 1, 'cancelled_by_driver', '2013-10-01 00:00:00');
INSERT INTO `trips` VALUES (3, 3, 12, 6, '    completed      ', '2013-10-01 00:00:00');
INSERT INTO `trips` VALUES (4, 4, 13, 6, 'cancelled_by_client', '2013-10-01 00:00:00');
INSERT INTO `trips` VALUES (5, 1, 10, 1, '    completed      ', '2013-10-01 00:00:00');
INSERT INTO `trips` VALUES (6, 2, 11, 6, '    completed      ', '2013-10-01 00:00:00');
INSERT INTO `trips` VALUES (7, 3, 12, 6, '    completed      ', '2013-10-01 00:00:00');
INSERT INTO `trips` VALUES (8, 2, 12, 12, '    completed      ', '2013-10-01 00:00:00');
INSERT INTO `trips` VALUES (9, 3, 10, 12, '    completed      ', '2013-10-01 00:00:00');
INSERT INTO `trips` VALUES (10, 4, 13, 12, 'cancelled_by_driver', '2013-10-01 00:00:00');

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users`  (
  `Users_Id` int(11) NOT NULL AUTO_INCREMENT,
  `Banned` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `Role` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`Users_Id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 14 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO `users` VALUES (1, 'No ', 'client');
INSERT INTO `users` VALUES (2, 'Yes', 'client');
INSERT INTO `users` VALUES (3, 'No ', 'client');
INSERT INTO `users` VALUES (4, 'No ', 'client');
INSERT INTO `users` VALUES (10, 'No ', 'driver');
INSERT INTO `users` VALUES (11, 'No ', 'driver');
INSERT INTO `users` VALUES (12, 'No ', 'driver');
INSERT INTO `users` VALUES (13, 'No ', 'driver');

-- ----------------------------
-- Table structure for weather
-- ----------------------------
DROP TABLE IF EXISTS `weather`;
CREATE TABLE `weather`  (
  `Id` int(11) NOT NULL,
  `RecordDate` datetime(0) NULL DEFAULT NULL,
  `Temperature` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`Id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of weather
-- ----------------------------
INSERT INTO `weather` VALUES (1, '2015-01-01 00:00:00', 10);
INSERT INTO `weather` VALUES (2, '2015-01-02 00:00:00', 25);
INSERT INTO `weather` VALUES (3, '2015-01-03 00:00:00', 20);
INSERT INTO `weather` VALUES (4, '2015-01-04 00:00:00', 30);

-- ----------------------------
-- Function structure for getNthHighestSalary
-- ----------------------------
DROP FUNCTION IF EXISTS `getNthHighestSalary`;
delimiter ;;
CREATE FUNCTION `getNthHighestSalary`(N INT)
 RETURNS int(11)
BEGIN
  RETURN (
      # Write your MySQL query statement below.
        select(
        SELECT DISTINCT Salary FROM Employee ORDER BY Salary DESC LIMIT N,1
      )
  );
END
;;
delimiter ;

SET FOREIGN_KEY_CHECKS = 1;
