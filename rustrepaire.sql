-- phpMyAdmin SQL Dump
-- version 4.2.7.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Nov 30, 2020 at 06:36 PM
-- Server version: 5.6.20
-- PHP Version: 5.5.15

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `rustrepaire`
--

-- --------------------------------------------------------

--
-- Table structure for table `employee`
--

CREATE TABLE IF NOT EXISTS `employee` (
  `Name` varchar(50) NOT NULL,
  `Phone` varchar(12) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `employee`
--

INSERT INTO `employee` (`Name`, `Phone`) VALUES
('Emp1', '0755022125'),
('Emp2', '0766325325'),
('Emp3', '0788522125');

-- --------------------------------------------------------

--
-- Table structure for table `repairjobs`
--

CREATE TABLE IF NOT EXISTS `repairjobs` (
`rjid` int(100) NOT NULL,
  `date` varchar(20) NOT NULL,
  `ownername` varchar(50) NOT NULL,
  `contactno` varchar(20) NOT NULL,
  `vechicleno` varchar(20) NOT NULL,
  `description` varchar(100) NOT NULL
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=10 ;

--
-- Dumping data for table `repairjobs`
--

INSERT INTO `repairjobs` (`rjid`, `date`, `ownername`, `contactno`, `vechicleno`, `description`) VALUES
(9, '12/02/14', 'Lasi', '075', '2526', 'not assigned');

-- --------------------------------------------------------

--
-- Table structure for table `spareparts`
--

CREATE TABLE IF NOT EXISTS `spareparts` (
`spid` int(100) NOT NULL,
  `partsname` varchar(100) NOT NULL,
  `count` int(100) NOT NULL
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=3 ;

--
-- Dumping data for table `spareparts`
--

INSERT INTO `spareparts` (`spid`, `partsname`, `count`) VALUES
(1, 'mirrors', 100),
(2, 'tyres', 50);

-- --------------------------------------------------------

--
-- Table structure for table `supplier`
--

CREATE TABLE IF NOT EXISTS `supplier` (
  `RegNo` varchar(10) NOT NULL,
  `SupplierName` varchar(65) NOT NULL,
  `Address` varchar(200) NOT NULL,
  `LandlineNo` varchar(20) NOT NULL,
  `MobileNo` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `supplier`
--

INSERT INTO `supplier` (`RegNo`, `SupplierName`, `Address`, `LandlineNo`, `MobileNo`) VALUES
('S1001', 'AbuDaBst', 'Colombo', '0112686272', '0755044128');

-- --------------------------------------------------------

--
-- Table structure for table `vehiclerestoration`
--

CREATE TABLE IF NOT EXISTS `vehiclerestoration` (
`vrid` int(100) NOT NULL,
  `date` varchar(10) NOT NULL,
  `ownername` varchar(50) NOT NULL,
  `contactno` varchar(20) NOT NULL,
  `vechileno` varchar(10) NOT NULL,
  `description` varchar(50) NOT NULL
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=3 ;

--
-- Dumping data for table `vehiclerestoration`
--

INSERT INTO `vehiclerestoration` (`vrid`, `date`, `ownername`, `contactno`, `vechileno`, `description`) VALUES
(2, '12/01/12', 'Dhanurjan', '077', '1213', '');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `repairjobs`
--
ALTER TABLE `repairjobs`
 ADD PRIMARY KEY (`rjid`);

--
-- Indexes for table `spareparts`
--
ALTER TABLE `spareparts`
 ADD PRIMARY KEY (`spid`);

--
-- Indexes for table `supplier`
--
ALTER TABLE `supplier`
 ADD PRIMARY KEY (`RegNo`);

--
-- Indexes for table `vehiclerestoration`
--
ALTER TABLE `vehiclerestoration`
 ADD PRIMARY KEY (`vrid`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `repairjobs`
--
ALTER TABLE `repairjobs`
MODIFY `rjid` int(100) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=10;
--
-- AUTO_INCREMENT for table `spareparts`
--
ALTER TABLE `spareparts`
MODIFY `spid` int(100) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT for table `vehiclerestoration`
--
ALTER TABLE `vehiclerestoration`
MODIFY `vrid` int(100) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=3;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
