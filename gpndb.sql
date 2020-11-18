-- phpMyAdmin SQL Dump
-- version 4.9.0.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Nov 18, 2020 at 01:56 PM
-- Server version: 10.4.6-MariaDB
-- PHP Version: 7.3.9

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `gpndb`
--

-- --------------------------------------------------------

--
-- Table structure for table `student`
--

CREATE TABLE `student` (
  `rollno` int(11) NOT NULL,
  `name` varchar(200) NOT NULL,
  `branch` varchar(20) NOT NULL,
  `mobile` bigint(13) NOT NULL,
  `percentage` float NOT NULL,
  `fees` varchar(20) NOT NULL DEFAULT 'Not Paid'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `student`
--

INSERT INTO `student` (`rollno`, `name`, `branch`, `mobile`, `percentage`, `fees`) VALUES
(176101, 'Suraj ', 'Computer Tech.', 3691597531, 98, 'Paid'),
(176102, 'Nidhi', 'CM', 9856985474, 0, 'Paid'),
(176103, 'Aniket', 'Computer Tech.', 7885252520, 98.44, 'Paid'),
(176104, 'Pradnya', 'Computer Tech.', 9784513231, 87.45, 'Paid'),
(176105, 'Jui', 'Computer Tech.', 7885252520, 98.44, 'Paid'),
(176106, 'Gaurav ', 'Computer Tech.', 3691597531, 98, 'Paid'),
(176107, 'Tanuja', 'Computer Tech.', 78965418569, 22, 'Paid'),
(176108, 'Purnima', 'Computer Tech.', 789136541, 78, 'Not Paid'),
(176109, 'Diksha', 'Computer Tech.', 8885411531, 98.21, ' Not Paid'),
(176110, 'Praful', 'Computer Tech.', 9876543521, 95, 'Not Paid'),
(176111, 'Prajkta', 'Computer Tech.', 9822346422, 97, 'Paid'),
(176112, 'Shaurya', 'IF', 8379895587, 92.5, 'Paid'),
(176113, 'Sarvesh', 'IF', 9512367895, 89, ' Not Paid');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `student`
--
ALTER TABLE `student`
  ADD PRIMARY KEY (`rollno`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `student`
--
ALTER TABLE `student`
  MODIFY `rollno` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=179636;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
