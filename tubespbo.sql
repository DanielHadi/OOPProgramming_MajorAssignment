-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jan 09, 2023 at 01:50 AM
-- Server version: 10.4.24-MariaDB
-- PHP Version: 7.4.29

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `tubespbo`
--

-- --------------------------------------------------------

--
-- Table structure for table `barang`
--

CREATE TABLE `barang` (
  `id_barang` int(10) NOT NULL,
  `id_transaksi` varchar(20) DEFAULT NULL,
  `nama_barang` varchar(10) NOT NULL,
  `berat` double(10,2) NOT NULL,
  `nama_penerima` varchar(30) NOT NULL,
  `terkirim` tinyint(1) DEFAULT NULL,
  `jarak` double(4,2) NOT NULL,
  `alamat_pengiriman` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `barang`
--

INSERT INTO `barang` (`id_barang`, `id_transaksi`, `nama_barang`, `berat`, `nama_penerima`, `terkirim`, `jarak`, `alamat_pengiriman`) VALUES
(106, 'q_1', 'dasd', 1.00, 'assaa', NULL, 1.00, 'assaa'),
(107, 'q_1', 'sss', 1.00, 'asd', NULL, 1.00, 'asd'),
(109, 'q_2', 'dasd', 2.00, 'adsad', NULL, 1.00, 'adsad');

-- --------------------------------------------------------

--
-- Table structure for table `jmltransaksi`
--

CREATE TABLE `jmltransaksi` (
  `username` varchar(30) NOT NULL,
  `jumlah` int(11) NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `jmltransaksi`
--

INSERT INTO `jmltransaksi` (`username`, `jumlah`) VALUES
('arunikaaka', 0),
('derderder', 0),
('ichsansadb', 0),
('noobmaster', 0),
('q', 8),
('syafaza', 0);

-- --------------------------------------------------------

--
-- Table structure for table `transaksi`
--

CREATE TABLE `transaksi` (
  `id_transaksi` varchar(10) NOT NULL,
  `harga_pengiriman` int(11) NOT NULL,
  `tanggal_kirim` date DEFAULT NULL,
  `username` varchar(30) NOT NULL,
  `status_pengiriman` tinyint(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `transaksi`
--

INSERT INTO `transaksi` (`id_transaksi`, `harga_pengiriman`, `tanggal_kirim`, `username`, `status_pengiriman`) VALUES
('q_1', 0, NULL, 'q', NULL),
('q_2', 0, NULL, 'q', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `id_user` varchar(10) NOT NULL,
  `nama` varchar(30) NOT NULL,
  `username` varchar(10) NOT NULL,
  `no_hp` varchar(15) NOT NULL,
  `password` varchar(8) NOT NULL,
  `role` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`id_user`, `nama`, `username`, `no_hp`, `password`, `role`) VALUES
('adm_001', 'Irvan', 'admin1', '0812345', 'irvangtg', 'admin'),
('adm_002', 'Ratna', 'admin2', '0856789', 'ratnactk', 'admin'),
('krr_001', 'Agus', 'agusyoi', '0875463', 'agus123', 'kurir'),
('krr_002', 'Andi', 'andiskuy', '0874325', 'andi123', 'kurir'),
('krr_003', 'Udin', 'udinpt', '0845671', 'udin123', 'kurir'),
('snd_001', 'daniel', 'noobmaster', '0874312', 'daniel45', 'sender'),
('snd_002', 'ichsan', 'ichsansadb', '0874369', 'ichsan45', 'sender'),
('snd_003', 'Hanif', 'arunikaaka', '0874985', 'hanif456', 'sender'),
('snd_004', 'syahdi', 'syafaza', '0865321', 'syahdi45', 'sender'),
('snd_005', 'derryl', 'derderder', '0846325', 'derryl45', 'sender'),
('snd_006', 'adasd', 'q', '333', '1', 'sender');

-- --------------------------------------------------------

--
-- Table structure for table `wrap`
--

CREATE TABLE `wrap` (
  `id_transaksi` varchar(15) DEFAULT NULL,
  `nama_barang` varchar(10) NOT NULL,
  `berat` double(10,2) NOT NULL,
  `nama_penerima` varchar(30) NOT NULL,
  `jarak` double(4,2) NOT NULL,
  `alamat_pengiriman` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `barang`
--
ALTER TABLE `barang`
  ADD PRIMARY KEY (`id_barang`);

--
-- Indexes for table `jmltransaksi`
--
ALTER TABLE `jmltransaksi`
  ADD PRIMARY KEY (`username`);

--
-- Indexes for table `transaksi`
--
ALTER TABLE `transaksi`
  ADD PRIMARY KEY (`id_transaksi`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id_user`);

--
-- Indexes for table `wrap`
--
ALTER TABLE `wrap`
  ADD PRIMARY KEY (`nama_barang`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `barang`
--
ALTER TABLE `barang`
  MODIFY `id_barang` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=110;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
