-- phpMyAdmin SQL Dump
-- version 4.1.12
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Jun 02, 2016 at 08:00 AM
-- Server version: 5.6.16
-- PHP Version: 5.5.11

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `sad`
--

-- --------------------------------------------------------

--
-- Table structure for table `admin`
--

CREATE TABLE IF NOT EXISTS `admin` (
  `email` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `admin`
--

INSERT INTO `admin` (`email`, `password`) VALUES
('ronel110396@yahoo.com', 'ronel110396');

-- --------------------------------------------------------

--
-- Table structure for table `register`
--

CREATE TABLE IF NOT EXISTS `register` (
  `reg_id` int(11) NOT NULL AUTO_INCREMENT,
  `lastname` varchar(50) NOT NULL,
  `firstname` varchar(50) NOT NULL,
  `gender` varchar(10) NOT NULL,
  `birthday` varchar(20) NOT NULL,
  `email` varchar(50) NOT NULL,
  `homeaddress` varchar(50) NOT NULL,
  `contactnumber` varchar(20) NOT NULL,
  `password` varchar(50) NOT NULL,
  `image_type` varchar(20) NOT NULL,
  `ticket_load` varchar(20) NOT NULL,
  PRIMARY KEY (`reg_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=26 ;

--
-- Dumping data for table `register`
--

INSERT INTO `register` (`reg_id`, `lastname`, `firstname`, `gender`, `birthday`, `email`, `homeaddress`, `contactnumber`, `password`, `image_type`, `ticket_load`) VALUES
(12, 'Willis', 'Bruce', 'male', '2015-08-13', 'glenwinbernabe@yahoo.com', 'sa batcave', '09358217701', 'Glenwin18', 'jpg', ''),
(13, 'Lopz', 'Jason', 'male', '1997-02-21', 'jasonlopez@yahoo.com', '238-B Bacood Street Sta.Mesa,Manila', '09059242742', 'Jason123', 'jpg', ''),
(14, 'Bernabe', 'Glen', 'male', '2015-08-05', 'glenwinbernabe@gmail.com', 'Gate 17 Parola Tondo Manila ', '09288441078', 'Phantom03', 'jpg', '100'),
(19, 'Bernabe', 'Glen', 'male', '2015-08-13', 'tabitabi@gmail.com', 'diyan lang sa tabi tabi', '09358217701', 'Glenwin18', 'jpg', ''),
(20, 'Ichigo', 'Konan', 'male', '1996-09-16', 'detectiveconan@solution.com', '238-B Bacood Street Sta.Mesa,Manila', '09234562348', 'Ichigo409', '', ''),
(21, 'Cella', 'Freeza', 'male', '2015-10-10', 'dragonballz@badguys.info', 'Namek Earth', '09298802563', 'Password765', '', ''),
(22, 'Saflor', 'Lourrey', 'male', '2015-10-08', 'lourreylyn@gmail.com', '238-B Bacood Street Sta.Mesa,Manila', '09359715562', 'David2015', '', ''),
(23, 'Roldan', 'Ricel', 'male', '2015-10-01', 'ricelroldan@yahoo.com', '238-B Bacood Street Sta.Mesa,Manila', '09307352098', 'Phantom110396', '', '500'),
(25, 'Rosalinda', 'Ricasel', 'male', '1996-10-06', 'ricaselrolando@gmail.com', '238-B Bacood Street Sta.Mesa,Manila', '09324490278', 'Glenwin18', '', '');

-- --------------------------------------------------------

--
-- Table structure for table `schedule`
--

CREATE TABLE IF NOT EXISTS `schedule` (
  `email` varchar(50) NOT NULL,
  `from` varchar(30) NOT NULL,
  `to` varchar(30) NOT NULL,
  `date` varchar(30) NOT NULL,
  `time` varchar(30) NOT NULL,
  `lane` varchar(30) NOT NULL,
  `what_lrt` varchar(20) NOT NULL,
  `sched_id` int(11) NOT NULL AUTO_INCREMENT,
  `is_Paid` varchar(1) NOT NULL,
  PRIMARY KEY (`sched_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=6 ;

--
-- Dumping data for table `schedule`
--

INSERT INTO `schedule` (`email`, `from`, `to`, `date`, `time`, `lane`, `what_lrt`, `sched_id`, `is_Paid`) VALUES
('glenwinbernabe@gmail.com', 'Baclaran', 'Quirino', '2015-10-6', '10:31 PM', 'Lane 1', 'LRT 1', 3, ''),
('lourreylyn@gmail.com', 'C.M. Recto', 'Santolan', '2015-10-6', '11:8 PM', 'Lane 1', 'LRT 2', 4, ''),
('jasonlopez@yahoo.com', 'Magallanes', 'Ortigas', '2015-10-6', '11:13 PM', 'Lane 1', 'MRT 3', 5, '');

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE IF NOT EXISTS `user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(16) NOT NULL,
  `username` varchar(16) NOT NULL,
  `confirm` varchar(16) NOT NULL,
  `password` varchar(11) NOT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
