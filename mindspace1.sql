-- phpMyAdmin SQL Dump
-- version 4.9.7
-- https://www.phpmyadmin.net/
--
-- Host: localhost:8889
-- Generation Time: Mar 23, 2021 at 07:46 PM
-- Server version: 5.7.32
-- PHP Version: 7.4.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `mindspace1`
--

-- --------------------------------------------------------

--
-- Table structure for table `admin`
--

CREATE TABLE `admin` (
  `cin` int(11) NOT NULL,
  `nom` varchar(50) NOT NULL,
  `prenom` varchar(50) NOT NULL,
  `sexe` varchar(50) NOT NULL,
  `datee` date NOT NULL,
  `email` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `telephone` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `admin`
--

INSERT INTO `admin` (`cin`, `nom`, `prenom`, `sexe`, `datee`, `email`, `password`, `telephone`) VALUES
(122, '12', '12', 'Homme', '2021-03-09', 'admin', 'admin', 444);

-- --------------------------------------------------------

--
-- Table structure for table `coach`
--

CREATE TABLE `coach` (
  `cin` int(11) NOT NULL,
  `nom` varchar(50) NOT NULL,
  `prenom` varchar(50) NOT NULL,
  `sexe` varchar(50) NOT NULL,
  `datee` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `coach`
--

INSERT INTO `coach` (`cin`, `nom`, `prenom`, `sexe`, `datee`) VALUES
(1, 'mohamed', 'bouslah', 'Homme', '2021-03-09'),
(2, 'aziz', 'ncir', 'Homme', '2021-03-08'),
(3, 'achref', 'Baraketi', 'Homme', '2021-03-08'),
(4, 'zaineb', 'z', 'Femme', '2021-03-08'),
(4343, 'iyed', 'zidi', 'Homme', '2021-03-09');

-- --------------------------------------------------------

--
-- Table structure for table `membre`
--

CREATE TABLE `membre` (
  `cin` int(11) NOT NULL,
  `nom` varchar(50) NOT NULL,
  `prenom` varchar(50) NOT NULL,
  `sexe` varchar(50) NOT NULL,
  `datee` date NOT NULL,
  `taille` float NOT NULL,
  `poids` float NOT NULL,
  `email` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `telephone` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `membre`
--

INSERT INTO `membre` (`cin`, `nom`, `prenom`, `sexe`, `datee`, `taille`, `poids`, `email`, `password`, `telephone`) VALUES
(123, 'aziz', 'salah', 'Homme', '2021-03-16', 6, 7, 'salahaziz.ncir@gmail.com', 'MOHAMED', 5),
(321, 'achref', 'baraketi', 'Homme', '2021-03-09', 180, 70, 'achref', 'AZERTY', 54),
(1323, 'med', 'med', 'Femme', '2021-03-16', 66, 77, 'medbouslah@gmail.com', '21', 55),
(5454, 'FARAH', 'FFD', 'Femme', '2021-03-10', 123, 444, '44', '44', 44);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `coach`
--
ALTER TABLE `coach`
  ADD PRIMARY KEY (`cin`);

--
-- Indexes for table `membre`
--
ALTER TABLE `membre`
  ADD PRIMARY KEY (`cin`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
