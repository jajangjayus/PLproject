-- phpMyAdmin SQL Dump
-- version 4.7.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Nov 02, 2017 at 08:03 AM
-- Server version: 10.1.25-MariaDB
-- PHP Version: 7.1.7

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `ujicoba`
--

-- --------------------------------------------------------

--
-- Table structure for table `invoice`
--

CREATE TABLE `invoice` (
  `id_invoice` int(10) NOT NULL,
  `no_po` varchar(20) DEFAULT NULL,
  `no_surat_call` varchar(20) DEFAULT NULL,
  `no_surat_jalan` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `invoice`
--

INSERT INTO `invoice` (`id_invoice`, `no_po`, `no_surat_call`, `no_surat_jalan`) VALUES
(1, NULL, NULL, '01229');

-- --------------------------------------------------------

--
-- Table structure for table `log`
--

CREATE TABLE `log` (
  `id_log` int(11) NOT NULL,
  `id_user` int(11) DEFAULT NULL,
  `create_date` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `log`
--

INSERT INTO `log` (`id_log`, `id_user`, `create_date`) VALUES
(1, 1, '2017-08-26'),
(2, 8, '2017-08-26'),
(3, 1, '2017-08-26'),
(4, 8, '2017-08-26'),
(5, 8, '2017-08-26'),
(6, 8, '2017-08-26'),
(7, 8, '2017-08-26'),
(8, 8, '2017-08-26'),
(9, 8, '2017-08-27'),
(10, 8, '2017-08-27'),
(11, 8, '2017-08-27'),
(12, 8, '2017-08-27'),
(13, 8, '2017-08-27'),
(14, 8, '2017-08-27'),
(15, 8, '2017-08-27'),
(16, 8, '2017-08-27'),
(17, 8, '2017-08-27'),
(18, 8, '2017-08-27'),
(19, 1, '2017-10-23'),
(20, 1, '2017-10-23'),
(21, 1, '2017-10-24'),
(22, 1, '2017-10-24'),
(23, 1, '2017-10-24'),
(24, 1, '2017-10-24'),
(25, 1, '2017-10-24'),
(26, 1, '2017-10-25'),
(27, 1, '2017-10-25'),
(28, 1, '2017-10-25'),
(29, 1, '2017-10-25'),
(30, 1, '2017-10-25'),
(31, 1, '2017-10-25'),
(32, 1, '2017-10-25'),
(33, 1, '2017-10-25'),
(34, 1, '2017-10-25'),
(35, 1, '2017-10-25'),
(36, 1, '2017-10-25'),
(37, 1, '2017-10-25'),
(38, 1, '2017-10-25'),
(39, 1, '2017-10-25'),
(40, 1, '2017-10-25'),
(41, 1, '2017-10-25'),
(42, 1, '2017-10-25'),
(43, 1, '2017-10-25'),
(44, 1, '2017-10-25'),
(45, 1, '2017-10-25'),
(46, 1, '2017-10-25'),
(47, 1, '2017-10-25'),
(48, 1, '2017-10-25'),
(49, 1, '2017-10-25'),
(50, 1, '2017-10-25'),
(51, 1, '2017-10-25'),
(52, 1, '2017-10-25'),
(53, 1, '2017-10-25'),
(54, 1, '2017-10-25'),
(55, 1, '2017-10-25'),
(56, 1, '2017-10-25'),
(57, 1, '2017-10-25'),
(58, 1, '2017-10-25'),
(59, 1, '2017-10-25'),
(60, 1, '2017-10-25'),
(61, 1, '2017-10-25'),
(62, 1, '2017-10-25'),
(63, 1, '2017-10-25'),
(64, 1, '2017-10-25'),
(65, 1, '2017-10-25'),
(66, 1, '2017-10-25'),
(67, 1, '2017-10-25'),
(68, 1, '2017-10-25'),
(69, 1, '2017-10-25'),
(70, 1, '2017-10-25'),
(71, 1, '2017-10-25'),
(72, 1, '2017-10-25'),
(73, 1, '2017-10-25'),
(74, 1, '2017-10-25'),
(75, 1, '2017-10-25'),
(76, 1, '2017-10-25'),
(77, 1, '2017-10-25'),
(78, 1, '2017-10-25'),
(79, 1, '2017-10-25'),
(80, 1, '2017-10-25'),
(81, 1, '2017-10-25'),
(82, 1, '2017-10-25'),
(83, 1, '2017-10-25'),
(84, 1, '2017-10-25'),
(85, 1, '2017-10-25'),
(86, 1, '2017-10-25'),
(87, 1, '2017-10-25'),
(88, 1, '2017-10-25'),
(89, 1, '2017-10-25'),
(90, 1, '2017-10-25'),
(91, 1, '2017-10-25'),
(92, 1, '2017-10-25'),
(93, 1, '2017-10-25'),
(94, 1, '2017-10-25'),
(95, 1, '2017-10-25'),
(96, 1, '2017-10-25'),
(97, 1, '2017-10-25'),
(98, 1, '2017-10-25'),
(99, 1, '2017-10-25'),
(100, 1, '2017-10-25'),
(101, 7, '2017-10-25'),
(102, 19, '2017-10-25'),
(103, 19, '2017-10-25'),
(104, 19, '2017-10-25'),
(105, 19, '2017-10-26'),
(106, 19, '2017-10-26'),
(107, 19, '2017-10-26'),
(108, 19, '2017-10-26'),
(109, 19, '2017-10-26'),
(110, 20, '2017-10-26'),
(111, 20, '2017-10-26'),
(112, 20, '2017-10-26'),
(113, 20, '2017-10-26'),
(114, 20, '2017-10-26'),
(115, 20, '2017-10-26'),
(116, 20, '2017-10-26'),
(117, 20, '2017-10-26'),
(118, 20, '2017-10-26'),
(119, 20, '2017-10-26'),
(120, 20, '2017-10-26'),
(121, 20, '2017-10-26'),
(122, 20, '2017-10-26'),
(123, 20, '2017-10-26'),
(124, 20, '2017-10-26'),
(125, 20, '2017-10-26'),
(126, 20, '2017-10-26'),
(127, 20, '2017-10-26'),
(128, 20, '2017-10-26'),
(129, 20, '2017-10-26'),
(130, 20, '2017-10-26'),
(131, 20, '2017-10-26'),
(132, 20, '2017-10-26'),
(133, 20, '2017-10-26'),
(134, 20, '2017-10-26'),
(135, 20, '2017-10-26'),
(136, 20, '2017-10-26'),
(137, 20, '2017-10-26'),
(138, 20, '2017-10-26'),
(139, 20, '2017-10-26'),
(140, 20, '2017-10-26'),
(141, 20, '2017-10-26'),
(142, 20, '2017-10-26'),
(143, 20, '2017-10-26'),
(144, 20, '2017-10-26'),
(145, 20, '2017-10-26'),
(146, 20, '2017-10-26'),
(147, 20, '2017-10-26'),
(148, 20, '2017-10-26'),
(149, 20, '2017-10-26'),
(150, 20, '2017-10-26'),
(151, 20, '2017-10-26'),
(152, 20, '2017-10-26'),
(153, 20, '2017-10-26'),
(154, 20, '2017-10-26'),
(155, 20, '2017-10-26'),
(156, 20, '2017-10-26'),
(157, 20, '2017-10-26'),
(158, 20, '2017-10-26'),
(159, 20, '2017-10-26'),
(160, 20, '2017-10-26'),
(161, 20, '2017-10-26'),
(162, 20, '2017-10-26'),
(163, 20, '2017-10-26'),
(164, 20, '2017-10-26'),
(165, 20, '2017-10-26'),
(166, 20, '2017-10-26'),
(167, 20, '2017-10-26'),
(168, 20, '2017-10-26'),
(169, 20, '2017-10-26'),
(170, 20, '2017-10-27'),
(171, 20, '2017-10-27'),
(172, 20, '2017-10-27'),
(173, 20, '2017-10-27'),
(174, 20, '2017-10-27'),
(175, 20, '2017-10-27'),
(176, 20, '2017-10-27'),
(177, 20, '2017-10-27'),
(178, 20, '2017-10-27'),
(179, 20, '2017-10-27'),
(180, 20, '2017-10-27'),
(181, 20, '2017-10-27'),
(182, 20, '2017-10-29'),
(183, 20, '2017-10-29'),
(184, 20, '2017-10-29'),
(185, 20, '2017-10-29'),
(186, 20, '2017-10-29'),
(187, 20, '2017-10-29'),
(188, 20, '2017-10-29'),
(189, 20, '2017-10-29'),
(190, 20, '2017-10-29'),
(191, 20, '2017-10-29'),
(192, 20, '2017-10-29'),
(193, 20, '2017-10-29'),
(194, 20, '2017-10-29'),
(195, 20, '2017-10-29'),
(196, 20, '2017-10-29'),
(197, 20, '2017-10-29'),
(198, 20, '2017-10-29'),
(199, 20, '2017-10-29'),
(200, 20, '2017-10-29'),
(201, 20, '2017-10-29'),
(202, 20, '2017-10-29'),
(203, 20, '2017-10-29'),
(204, 20, '2017-10-29'),
(205, 20, '2017-10-29'),
(206, 20, '2017-10-29'),
(207, 20, '2017-10-29'),
(208, 20, '2017-10-29'),
(209, 20, '2017-10-29'),
(210, 20, '2017-10-29'),
(211, 20, '2017-10-29'),
(212, 20, '2017-10-29'),
(213, 20, '2017-10-29'),
(214, 20, '2017-10-29'),
(215, 20, '2017-10-29'),
(216, 20, '2017-10-29'),
(217, 20, '2017-10-29'),
(218, 20, '2017-10-29'),
(219, 20, '2017-10-29'),
(220, 20, '2017-10-29'),
(221, 20, '2017-10-29'),
(222, 20, '2017-10-29'),
(223, 20, '2017-10-29'),
(224, 20, '2017-10-29'),
(225, 20, '2017-10-29'),
(226, 20, '2017-10-29'),
(227, 20, '2017-10-29'),
(228, 20, '2017-10-30'),
(229, 20, '2017-10-30'),
(230, 20, '2017-10-30'),
(231, 20, '2017-10-30'),
(232, 20, '2017-10-30'),
(233, 20, '2017-10-30'),
(234, 20, '2017-10-30'),
(235, 20, '2017-10-30'),
(236, 20, '2017-10-30'),
(237, 20, '2017-10-30'),
(238, 20, '2017-10-30'),
(239, 20, '2017-10-30'),
(240, 20, '2017-10-30'),
(241, 20, '2017-10-30'),
(242, 20, '2017-10-30'),
(243, 20, '2017-10-30'),
(244, 20, '2017-10-30'),
(245, 20, '2017-10-30'),
(246, 20, '2017-10-30'),
(247, 20, '2017-10-30'),
(248, 20, '2017-10-30'),
(249, 20, '2017-10-30'),
(250, 20, '2017-10-30'),
(251, 20, '2017-10-30'),
(252, 20, '2017-10-30'),
(253, 20, '2017-10-30'),
(254, 20, '2017-10-30'),
(255, 20, '2017-10-30'),
(256, 20, '2017-10-30'),
(257, 20, '2017-10-30'),
(258, 20, '2017-10-30'),
(259, 20, '2017-10-30'),
(260, 20, '2017-10-30'),
(261, 20, '2017-10-30'),
(262, 20, '2017-10-30'),
(263, 20, '2017-10-30'),
(264, 20, '2017-10-30'),
(265, 20, '2017-10-30'),
(266, 20, '2017-10-31'),
(267, 20, '2017-10-31'),
(268, 20, '2017-11-01'),
(269, 20, '2017-11-01'),
(270, 20, '2017-11-01'),
(271, 20, '2017-11-01'),
(272, 20, '2017-11-01'),
(273, 20, '2017-11-01'),
(274, 20, '2017-11-01'),
(275, 20, '2017-11-01'),
(276, 20, '2017-11-01'),
(277, 20, '2017-11-01'),
(278, 20, '2017-11-01'),
(279, 20, '2017-11-02'),
(280, 20, '2017-11-02'),
(281, 20, '2017-11-02'),
(282, 20, '2017-11-02'),
(283, 20, '2017-11-02'),
(284, 21, '2017-11-02'),
(285, 20, '2017-11-02'),
(286, 20, '2017-11-02'),
(287, 20, '2017-11-02'),
(288, 20, '2017-11-02'),
(289, 20, '2017-11-02'),
(290, 20, '2017-11-02'),
(291, 20, '2017-11-02'),
(292, 20, '2017-11-02'),
(293, 20, '2017-11-02'),
(294, 20, '2017-11-02'),
(295, 20, '2017-11-02'),
(296, 20, '2017-11-02'),
(297, 20, '2017-11-02'),
(298, 20, '2017-11-02'),
(299, 20, '2017-11-02'),
(300, 20, '2017-11-02'),
(301, 25, '2017-11-02'),
(302, 25, '2017-11-02'),
(303, 20, '2017-11-02'),
(304, 25, '2017-11-02'),
(305, 20, '2017-11-02'),
(306, 25, '2017-11-02'),
(307, 20, '2017-11-02'),
(308, 20, '2017-11-02'),
(309, 25, '2017-11-02'),
(310, 20, '2017-11-02'),
(311, 25, '2017-11-02'),
(312, 25, '2017-11-02'),
(313, 24, '2017-11-02'),
(314, 20, '2017-11-02'),
(315, 25, '2017-11-02');

-- --------------------------------------------------------

--
-- Table structure for table `mwh`
--

CREATE TABLE `mwh` (
  `no_MWH` varchar(30) NOT NULL,
  `no_form` varchar(20) DEFAULT NULL,
  `id_pemasok` varchar(30) DEFAULT NULL,
  `no_surat_jalan` varchar(20) DEFAULT NULL,
  `tgl_MWH` date DEFAULT NULL,
  `warna` varchar(20) DEFAULT NULL,
  `jmlh_pengiriman` varchar(20) DEFAULT NULL,
  `jmlh_di_test` varchar(20) DEFAULT NULL,
  `jenis_barang` varchar(20) DEFAULT NULL,
  `ukuran` varchar(50) DEFAULT NULL,
  `status` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `mwh`
--

INSERT INTO `mwh` (`no_MWH`, `no_form`, `id_pemasok`, `no_surat_jalan`, `tgl_MWH`, `warna`, `jmlh_pengiriman`, `jmlh_di_test`, `jenis_barang`, `ukuran`, `status`) VALUES
('MWH', 'FORM00', '7', 'SJ/03', '2017-10-23', 'Biru', '25', '20', 'Plastik', '4 LITER', 0),
('MWH00', 'FORM00', '4', 'SJ/00', '2017-10-23', 'Hitam', '12', '2', 'Plastik', '5 LITER', 1),
('MWH001', 'FORM01', '5', 'SJ/01', '2017-08-04', 'Biru', '100', '3', 'Plastik', '5 LITER', 0),
('MWH002', 'FORM02', '4', 'sj/02', '2017-08-03', 'Merah', '50', '20', 'Plastik', '4 LITER', 0),
('MWH003', 'FORM03', '5', 'SJ/04', '2017-10-25', 'Hitam', '10', '10', 'Plastik', '5 LITER', 0),
('MWH004', 'FORM04', '3', 'SJ/04', '2017-10-26', 'Merah', '15', '5', 'Plastik', '5 LITER', 1),
('MWH005', 'FORM005', '6', 'SJ/05', '2017-10-23', 'Merah', '15', '10', 'Plastik', '5 LITER', 0);

-- --------------------------------------------------------

--
-- Table structure for table `pemasok`
--

CREATE TABLE `pemasok` (
  `id_pemasok` int(10) NOT NULL,
  `nama_pemasok` varchar(30) DEFAULT NULL,
  `alamat` varchar(50) DEFAULT NULL,
  `Phone` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `pemasok`
--

INSERT INTO `pemasok` (`id_pemasok`, `nama_pemasok`, `alamat`, `Phone`) VALUES
(1, 'PT. ABADI PLASTIK', 'Gunung Sahari', '021 1234566'),
(2, 'PT ABC', 'Jiung', '092102101'),
(3, 'PT BCA', 'Kemayoran', '3474575675757'),
(4, 'PT. SOSRO', 'Kemayoran', '02323829'),
(5, 'PT. Angular Js', 'Jl. Gajah ', '1233434'),
(6, 'PT. Candahar', 'Jakarta', '082111455499'),
(7, 'PT. Busan Miji', 'Kemayoran Utara', '021 999999'),
(8, 'PT. SIma Auta', 'Cikini Barat', '021 1234566');

-- --------------------------------------------------------

--
-- Table structure for table `sample`
--

CREATE TABLE `sample` (
  `sample` int(11) NOT NULL,
  `no_MWH` varchar(30) DEFAULT NULL,
  `visual` varchar(10) DEFAULT NULL,
  `cavity_btl` varchar(10) DEFAULT NULL,
  `berat_btl` varchar(10) DEFAULT NULL,
  `tinggi_btl` varchar(10) DEFAULT NULL,
  `berat_cap` varchar(10) DEFAULT NULL,
  `tinggi_cap` varchar(10) DEFAULT NULL,
  `torsi_cap` varchar(10) DEFAULT NULL,
  `drop_test` varchar(10) DEFAULT NULL,
  `uji_volume` varchar(10) DEFAULT NULL,
  `tgl_test` date DEFAULT NULL,
  `id_user` int(20) DEFAULT NULL,
  `pemeriksa` varchar(30) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `sample`
--

INSERT INTO `sample` (`sample`, `no_MWH`, `visual`, `cavity_btl`, `berat_btl`, `tinggi_btl`, `berat_cap`, `tinggi_cap`, `torsi_cap`, `drop_test`, `uji_volume`, `tgl_test`, `id_user`, `pemeriksa`) VALUES
(1, 'MWH00', 'Baik', '1', '2', '6', '2', '6', '3', '1', '2', '2017-10-30', 20, 'Imam Setiawan'),
(2, 'MWH00', 'Baik', '7', '4', '1', '2', '8', '3', '1', '4', '2017-10-30', 20, 'Imam Setiawan'),
(1, 'MWH004', 'Baik', '1', '8', '7', '1', '8', '2', '3', '1', '2017-10-30', 20, 'Eka Budi'),
(2, 'MWH004', 'Baik', '1', '3', '1', '2', '9', '3', '7', '6', '2017-10-30', 20, 'Eka Budi'),
(3, 'MWH004', 'Baik', '1', '6', '5', '7', '4', '9', '8', '6', '2017-10-30', 20, 'Eka Budi'),
(4, 'MWH004', 'Baik', '2', '1', '4', '6', '7', '8', '9', '6', '2017-10-30', 20, 'Eka Budi'),
(5, 'MWH004', 'Baik', '2', '4', '1', '9', '3', '7', '2', '1', '2017-10-30', 20, 'Eka Budi');

-- --------------------------------------------------------

--
-- Table structure for table `tmpsample`
--

CREATE TABLE `tmpsample` (
  `Count` int(11) NOT NULL,
  `no_MWH` varchar(30) DEFAULT NULL,
  `visual` varchar(10) DEFAULT NULL,
  `cavity_btl` varchar(10) DEFAULT NULL,
  `berat_btl` varchar(10) DEFAULT NULL,
  `tinggi_btl` varchar(10) DEFAULT NULL,
  `berat_cap` varchar(10) DEFAULT NULL,
  `tinggi_cap` varchar(10) DEFAULT NULL,
  `torsi_cap` varchar(10) DEFAULT NULL,
  `drop_test` varchar(10) DEFAULT NULL,
  `uji_volume` varchar(10) DEFAULT NULL,
  `tgl_test` date DEFAULT NULL,
  `id_user` int(20) DEFAULT NULL,
  `pemeriksa` varbinary(30) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `id_user` int(20) NOT NULL,
  `username` varchar(20) DEFAULT NULL,
  `telepon` varchar(12) NOT NULL,
  `password` varchar(20) DEFAULT NULL,
  `namalengkap` varchar(50) DEFAULT NULL,
  `level` varchar(30) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`id_user`, `username`, `telepon`, `password`, `namalengkap`, `level`) VALUES
(19, 'jajang', '082111455499', '123', 'Jajang Jaenal Yusup', 'Developer'),
(20, 'agung', '08561405797', '123', 'Agung Saja', 'Developer'),
(24, 'bagus', '085632451287', 'PL123', 'Ida Bagus', 'Administrator'),
(25, 'wahe', '08736278162', '123', 'Wawan Hermawan', 'Penguji');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `invoice`
--
ALTER TABLE `invoice`
  ADD PRIMARY KEY (`id_invoice`);

--
-- Indexes for table `log`
--
ALTER TABLE `log`
  ADD PRIMARY KEY (`id_log`);

--
-- Indexes for table `mwh`
--
ALTER TABLE `mwh`
  ADD PRIMARY KEY (`no_MWH`);

--
-- Indexes for table `pemasok`
--
ALTER TABLE `pemasok`
  ADD PRIMARY KEY (`id_pemasok`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id_user`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `log`
--
ALTER TABLE `log`
  MODIFY `id_log` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=316;
--
-- AUTO_INCREMENT for table `pemasok`
--
ALTER TABLE `pemasok`
  MODIFY `id_pemasok` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;
--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `id_user` int(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=26;COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
