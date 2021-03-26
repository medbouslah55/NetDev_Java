-- phpMyAdmin SQL Dump
-- version 5.0.4
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le : ven. 26 mars 2021 à 21:49
-- Version du serveur :  10.4.17-MariaDB
-- Version de PHP : 8.0.1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `bdpidev`
--

-- --------------------------------------------------------

--
-- Structure de la table `abonnement`
--

CREATE TABLE `abonnement` (
  `id` int(11) NOT NULL,
  `titre` varchar(255) NOT NULL,
  `type` varchar(255) NOT NULL,
  `debut` date NOT NULL,
  `fin` date NOT NULL,
  `prix` float NOT NULL,
  `id_act` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Structure de la table `activite`
--

CREATE TABLE `activite` (
  `id_act` int(11) NOT NULL,
  `categorie_act` varchar(20) DEFAULT NULL,
  `nom_act` varchar(20) DEFAULT NULL,
  `prix_reservation` double DEFAULT NULL,
  `date_act` varchar(10) DEFAULT NULL,
  `capacite` int(11) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `id_centre` int(11) DEFAULT NULL,
  `cin_coach` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Structure de la table `admin`
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Structure de la table `centre`
--

CREATE TABLE `centre` (
  `id_centre` int(11) NOT NULL,
  `nom_centre` varchar(30) NOT NULL,
  `tel_centre` varchar(8) NOT NULL,
  `mail_centre` varchar(30) NOT NULL,
  `adr_centre` varchar(50) NOT NULL,
  `type_centre` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `centre`
--

INSERT INTO `centre` (`id_centre`, `nom_centre`, `tel_centre`, `mail_centre`, `adr_centre`, `type_centre`) VALUES
(2, 'cyssi', '955555', 'Meow', 'heybye', 'mise a jour'),
(9, 'c3', '955555', 'Meow', 'heybye', 'mise a jour'),
(10, 'Ami', '955555', 'Meow', 'heybye', 'mise a jour'),
(11, 'Ami', '955555', 'Meow', 'heybye', 'mise a jour'),
(12, 'Ami', '955555', 'Meow', 'heybye', 'mise a jour');

-- --------------------------------------------------------

--
-- Structure de la table `coach`
--

CREATE TABLE `coach` (
  `cin` int(11) NOT NULL,
  `nom` varchar(50) NOT NULL,
  `prenom` varchar(50) NOT NULL,
  `sexe` varchar(50) NOT NULL,
  `datee` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Structure de la table `evenement`
--

CREATE TABLE `evenement` (
  `id_pub` int(11) NOT NULL,
  `date_pub` varchar(255) NOT NULL,
  `date_even` varchar(255) NOT NULL,
  `image` varchar(255) NOT NULL,
  `sujet` varchar(255) NOT NULL,
  `id_centre` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Structure de la table `membre`
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Structure de la table `menu`
--

CREATE TABLE `menu` (
  `id_menu` int(11) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `num_jour` int(11) DEFAULT NULL,
  `matin` varchar(255) DEFAULT NULL,
  `matin_img` varchar(255) DEFAULT NULL,
  `dej` varchar(255) DEFAULT NULL,
  `dej_img` varchar(255) DEFAULT NULL,
  `denner` varchar(255) DEFAULT NULL,
  `denner_img` varchar(255) DEFAULT NULL,
  `total_calories` int(11) DEFAULT NULL,
  `id_regime` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Structure de la table `panier`
--

CREATE TABLE `panier` (
  `id_panier` int(11) NOT NULL,
  `nom_activite` varchar(255) NOT NULL,
  `prix` float NOT NULL,
  `quantite` int(11) NOT NULL,
  `total` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Structure de la table `reclamation`
--

CREATE TABLE `reclamation` (
  `id` int(11) NOT NULL,
  `nom` varchar(255) NOT NULL,
  `prenom` varchar(255) NOT NULL,
  `mail` varchar(255) NOT NULL,
  `type` varchar(255) NOT NULL,
  `date` date DEFAULT current_timestamp(),
  `description` text NOT NULL,
  `etat` varchar(255) DEFAULT 'En cours',
  `cin_membre` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Structure de la table `regime`
--

CREATE TABLE `regime` (
  `id_regime` int(11) NOT NULL,
  `description` varchar(255) NOT NULL,
  `type` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Structure de la table `reservation`
--

CREATE TABLE `reservation` (
  `id_reservation` int(11) NOT NULL,
  `nom` varchar(255) NOT NULL,
  `prenom` varchar(255) NOT NULL,
  `cin_membre` int(11) DEFAULT NULL,
  `date_act` date NOT NULL,
  `nb_place` int(11) NOT NULL,
  `id_act` int(11) DEFAULT NULL,
  `id_panier` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Structure de la table `status`
--

CREATE TABLE `status` (
  `id_pub` int(11) NOT NULL,
  `date_pub` varchar(255) NOT NULL,
  `text` varchar(255) NOT NULL,
  `id_centre` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `status`
--

INSERT INTO `status` (`id_pub`, `date_pub`, `text`, `id_centre`) VALUES
(1, '28/02/2021', 'ahla bik sahbi', NULL),
(3, '28/02/2021', 'ahla bik sahbi', NULL),
(4, '01/03/2021', 'ahla bik', NULL),
(5, '02/03/2021', 'ahla bik', NULL);

-- --------------------------------------------------------

--
-- Structure de la table `suivre`
--

CREATE TABLE `suivre` (
  `date_deb` date DEFAULT NULL,
  `nb_jour_restant` int(11) NOT NULL,
  `date_fin` date DEFAULT NULL,
  `id_regime` int(11) NOT NULL,
  `cin_membre` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Structure de la table `tracking`
--

CREATE TABLE `tracking` (
  `id_track` int(11) NOT NULL,
  `old_poids` varchar(50) DEFAULT NULL,
  `imc` double DEFAULT NULL,
  `cin_membre` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `abonnement`
--
ALTER TABLE `abonnement`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK_abonnement_act` (`id_act`);

--
-- Index pour la table `activite`
--
ALTER TABLE `activite`
  ADD PRIMARY KEY (`id_act`),
  ADD KEY `FK_act_centre` (`id_centre`),
  ADD KEY `FK_act_coach` (`cin_coach`);

--
-- Index pour la table `centre`
--
ALTER TABLE `centre`
  ADD PRIMARY KEY (`id_centre`);

--
-- Index pour la table `coach`
--
ALTER TABLE `coach`
  ADD PRIMARY KEY (`cin`);

--
-- Index pour la table `evenement`
--
ALTER TABLE `evenement`
  ADD PRIMARY KEY (`id_pub`),
  ADD KEY `FK_evt_centre` (`id_centre`);

--
-- Index pour la table `membre`
--
ALTER TABLE `membre`
  ADD PRIMARY KEY (`cin`);

--
-- Index pour la table `menu`
--
ALTER TABLE `menu`
  ADD PRIMARY KEY (`id_menu`),
  ADD KEY `FK_menu_regime` (`id_regime`);

--
-- Index pour la table `panier`
--
ALTER TABLE `panier`
  ADD PRIMARY KEY (`id_panier`);

--
-- Index pour la table `reclamation`
--
ALTER TABLE `reclamation`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK_reclamation_membre` (`cin_membre`);

--
-- Index pour la table `regime`
--
ALTER TABLE `regime`
  ADD PRIMARY KEY (`id_regime`);

--
-- Index pour la table `reservation`
--
ALTER TABLE `reservation`
  ADD PRIMARY KEY (`id_reservation`),
  ADD KEY `FK_reservation_membre` (`cin_membre`),
  ADD KEY `FK_reservation_act` (`id_act`),
  ADD KEY `FK_reservation_panier` (`id_panier`);

--
-- Index pour la table `status`
--
ALTER TABLE `status`
  ADD PRIMARY KEY (`id_pub`),
  ADD KEY `FK_status_centre` (`id_centre`);

--
-- Index pour la table `suivre`
--
ALTER TABLE `suivre`
  ADD PRIMARY KEY (`id_regime`,`cin_membre`),
  ADD KEY `FK_suivre_membre` (`cin_membre`),
  ADD KEY `id_regime` (`id_regime`);

--
-- Index pour la table `tracking`
--
ALTER TABLE `tracking`
  ADD PRIMARY KEY (`id_track`),
  ADD KEY `FK_track_membre` (`cin_membre`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `abonnement`
--
ALTER TABLE `abonnement`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `activite`
--
ALTER TABLE `activite`
  MODIFY `id_act` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `centre`
--
ALTER TABLE `centre`
  MODIFY `id_centre` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;

--
-- AUTO_INCREMENT pour la table `evenement`
--
ALTER TABLE `evenement`
  MODIFY `id_pub` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=33;

--
-- AUTO_INCREMENT pour la table `menu`
--
ALTER TABLE `menu`
  MODIFY `id_menu` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `panier`
--
ALTER TABLE `panier`
  MODIFY `id_panier` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `reclamation`
--
ALTER TABLE `reclamation`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `regime`
--
ALTER TABLE `regime`
  MODIFY `id_regime` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `reservation`
--
ALTER TABLE `reservation`
  MODIFY `id_reservation` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `status`
--
ALTER TABLE `status`
  MODIFY `id_pub` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT pour la table `tracking`
--
ALTER TABLE `tracking`
  MODIFY `id_track` int(11) NOT NULL AUTO_INCREMENT;

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `abonnement`
--
ALTER TABLE `abonnement`
  ADD CONSTRAINT `FK_abonnement_act` FOREIGN KEY (`id_act`) REFERENCES `activite` (`id_act`);

--
-- Contraintes pour la table `activite`
--
ALTER TABLE `activite`
  ADD CONSTRAINT `FK_act_centre` FOREIGN KEY (`id_centre`) REFERENCES `centre` (`id_centre`),
  ADD CONSTRAINT `FK_act_coach` FOREIGN KEY (`cin_coach`) REFERENCES `coach` (`cin`);

--
-- Contraintes pour la table `evenement`
--
ALTER TABLE `evenement`
  ADD CONSTRAINT `FK_evt_centre` FOREIGN KEY (`id_centre`) REFERENCES `centre` (`id_centre`);

--
-- Contraintes pour la table `menu`
--
ALTER TABLE `menu`
  ADD CONSTRAINT `FK_menu_regime` FOREIGN KEY (`id_regime`) REFERENCES `regime` (`id_regime`);

--
-- Contraintes pour la table `reclamation`
--
ALTER TABLE `reclamation`
  ADD CONSTRAINT `FK_reclamation_membre` FOREIGN KEY (`cin_membre`) REFERENCES `membre` (`cin`);

--
-- Contraintes pour la table `reservation`
--
ALTER TABLE `reservation`
  ADD CONSTRAINT `FK_reservation_act` FOREIGN KEY (`id_act`) REFERENCES `activite` (`id_act`),
  ADD CONSTRAINT `FK_reservation_membre` FOREIGN KEY (`cin_membre`) REFERENCES `membre` (`cin`),
  ADD CONSTRAINT `FK_reservation_panier` FOREIGN KEY (`id_panier`) REFERENCES `panier` (`id_panier`);

--
-- Contraintes pour la table `status`
--
ALTER TABLE `status`
  ADD CONSTRAINT `FK_status_centre` FOREIGN KEY (`id_centre`) REFERENCES `centre` (`id_centre`);

--
-- Contraintes pour la table `suivre`
--
ALTER TABLE `suivre`
  ADD CONSTRAINT `FK_suivre_membre` FOREIGN KEY (`cin_membre`) REFERENCES `membre` (`cin`),
  ADD CONSTRAINT `FK_suivre_regime` FOREIGN KEY (`id_regime`) REFERENCES `regime` (`id_regime`);

--
-- Contraintes pour la table `tracking`
--
ALTER TABLE `tracking`
  ADD CONSTRAINT `FK_track_membre` FOREIGN KEY (`cin_membre`) REFERENCES `membre` (`cin`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
