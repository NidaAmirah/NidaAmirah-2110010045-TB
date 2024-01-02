-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Dec 16, 2023 at 09:04 PM
-- Server version: 10.4.28-MariaDB
-- PHP Version: 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `aplikasi_inventaris_aset`
--

-- --------------------------------------------------------

--
-- Table structure for table `aset`
--

CREATE TABLE `aset` (
  `kd_aset` varchar(50) NOT NULL,
  `nama_aset` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `aset`
--

INSERT INTO `aset` (`kd_aset`, `nama_aset`) VALUES
('JK-SSKS', 'SEMEN'),
('JPX-SS', 'Tanahh'),
('KO-SS', 'DDL');

-- --------------------------------------------------------

--
-- Table structure for table `login`
--

CREATE TABLE `login` (
  `user` varchar(50) NOT NULL,
  `password` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `login`
--

INSERT INTO `login` (`user`, `password`) VALUES
('admin', 'admin');

-- --------------------------------------------------------

--
-- Table structure for table `lokasi`
--

CREATE TABLE `lokasi` (
  `kd_lokasi` varchar(50) NOT NULL,
  `nama_lokasi` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `lokasi`
--

INSERT INTO `lokasi` (`kd_lokasi`, `nama_lokasi`) VALUES
('001', 'Gudang');

-- --------------------------------------------------------

--
-- Table structure for table `penempatan`
--

CREATE TABLE `penempatan` (
  `kd_penempatan` varchar(50) NOT NULL,
  `kd_aset` varchar(50) NOT NULL,
  `kd_lokasi` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `penempatan`
--

INSERT INTO `penempatan` (`kd_penempatan`, `kd_aset`, `kd_lokasi`) VALUES
('addadad', 'JK-SSKS', '001'),
('K-ASJJy', 'JK-SSKS', '001'),
('K-XSS', 'JK-SSKS', '001');

-- --------------------------------------------------------

--
-- Table structure for table `transaksi_keluar`
--

CREATE TABLE `transaksi_keluar` (
  `kd_keluar` varchar(50) NOT NULL,
  `tanggal` varchar(50) NOT NULL,
  `kd_aset` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `transaksi_keluar`
--

INSERT INTO `transaksi_keluar` (`kd_keluar`, `tanggal`, `kd_aset`) VALUES
('ajskaj', 'Thu Dec 07 19:18:51 SGT 2023', 'JK-SSKS'),
('KX-KOS', 'Sun Dec 10 21:26:43 SGT 2023', 'JK-SSKS');

-- --------------------------------------------------------

--
-- Table structure for table `transaksi_masuk`
--

CREATE TABLE `transaksi_masuk` (
  `kd_masuk` varchar(50) NOT NULL,
  `tanggal` varchar(50) NOT NULL,
  `kd_aset` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `transaksi_masuk`
--

INSERT INTO `transaksi_masuk` (`kd_masuk`, `tanggal`, `kd_aset`) VALUES
('hugjug', 'Mon Dec 25 23:07:02 SGT 2023', 'JK-SSKS'),
('K-ASJJS', 'Tue Dec 12 19:45:50 SGT 2023', 'JPX-SS'),
('QWQW', 'Tue Dec 12 21:17:00 SGT 2023', 'JK-SSKS');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `aset`
--
ALTER TABLE `aset`
  ADD PRIMARY KEY (`kd_aset`);

--
-- Indexes for table `login`
--
ALTER TABLE `login`
  ADD PRIMARY KEY (`user`);

--
-- Indexes for table `lokasi`
--
ALTER TABLE `lokasi`
  ADD PRIMARY KEY (`kd_lokasi`);

--
-- Indexes for table `penempatan`
--
ALTER TABLE `penempatan`
  ADD PRIMARY KEY (`kd_penempatan`),
  ADD KEY `kd_aset` (`kd_aset`),
  ADD KEY `kd_lokasi` (`kd_lokasi`);

--
-- Indexes for table `transaksi_keluar`
--
ALTER TABLE `transaksi_keluar`
  ADD PRIMARY KEY (`kd_keluar`),
  ADD KEY `kd_aset` (`kd_aset`);

--
-- Indexes for table `transaksi_masuk`
--
ALTER TABLE `transaksi_masuk`
  ADD PRIMARY KEY (`kd_masuk`),
  ADD KEY `kd_aset` (`kd_aset`);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `penempatan`
--
ALTER TABLE `penempatan`
  ADD CONSTRAINT `penempatan_ibfk_1` FOREIGN KEY (`kd_aset`) REFERENCES `aset` (`kd_aset`),
  ADD CONSTRAINT `penempatan_ibfk_2` FOREIGN KEY (`kd_lokasi`) REFERENCES `lokasi` (`kd_lokasi`);

--
-- Constraints for table `transaksi_keluar`
--
ALTER TABLE `transaksi_keluar`
  ADD CONSTRAINT `transaksi_keluar_ibfk_1` FOREIGN KEY (`kd_aset`) REFERENCES `aset` (`kd_aset`);

--
-- Constraints for table `transaksi_masuk`
--
ALTER TABLE `transaksi_masuk`
  ADD CONSTRAINT `transaksi_masuk_ibfk_1` FOREIGN KEY (`kd_aset`) REFERENCES `aset` (`kd_aset`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
