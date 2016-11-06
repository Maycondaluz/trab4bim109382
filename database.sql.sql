/*
SQLyog Ultimate v11.11 (64 bit)
MySQL - 5.5.5-10.1.16-MariaDB : Database - trab4bim103982
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`trab4bim103982` /*!40100 DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci */;

USE `trab4bim103982`;

/*Table structure for table `tb_pessoa` */

DROP TABLE IF EXISTS `tb_pessoa`;

CREATE TABLE `tb_pessoa` (
  `id_pessoa` int(11) NOT NULL AUTO_INCREMENT COMMENT 'CÓDIGO DA PESSOA',
  `nm_pessoa` varchar(70) COLLATE utf8_unicode_ci NOT NULL COMMENT 'NOME DA PESSOA',
  `fl_sexo` char(1) COLLATE utf8_unicode_ci NOT NULL COMMENT 'INFORMAR M OU F',
  `dt_cadastro` datetime NOT NULL COMMENT 'DATA DE CADASTRO DO REGISTRO',
  `ds_email` varchar(80) COLLATE utf8_unicode_ci NOT NULL COMMENT 'EMAIL DA PESSOA',
  `ds_endereco` varchar(200) COLLATE utf8_unicode_ci NOT NULL COMMENT 'DESCRIÇÃO DO ENDEREÇO',
  `fl_origemCadastro` char(1) COLLATE utf8_unicode_ci NOT NULL COMMENT 'ORIGEM DO CADASTRO (I) = INPUT OU (X) = XML',
  `id_usuario_cadastro` int(11) NOT NULL COMMENT 'USUÁRIO LOGADO QUE CADASTROU A PESSOA',
  PRIMARY KEY (`id_pessoa`),
  KEY `id_usuario_cadastro` (`id_usuario_cadastro`),
  CONSTRAINT `tb_pessoa_ibfk_1` FOREIGN KEY (`id_usuario_cadastro`) REFERENCES `tb_usuario` (`id_usuario`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `tb_pessoa` */

/*Table structure for table `tb_usuario` */

DROP TABLE IF EXISTS `tb_usuario`;

CREATE TABLE `tb_usuario` (
  `id_usuario` int(11) NOT NULL AUTO_INCREMENT COMMENT 'CÓDIGO DO USUÁRIO',
  `ds_login` varchar(30) COLLATE utf8_unicode_ci NOT NULL COMMENT 'LOGIN DO USUÁRIO PARA ACESSO AO SISTEMA',
  `ds_senha` varchar(30) COLLATE utf8_unicode_ci NOT NULL COMMENT 'SENHA DO USUÁRIO PARA ACESSO AO SISTEMA',
  PRIMARY KEY (`id_usuario`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `tb_usuario` */

insert  into `tb_usuario`(`id_usuario`,`ds_login`,`ds_senha`) values (1,'admin','123456');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
