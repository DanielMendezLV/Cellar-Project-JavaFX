--drop database dbBodega
CREATE DATABASE dbBodega
GO
USE dbBodega
GO
CREATE TABLE Usuario(
	idUsuario INT IDENTITY(1,1),
	nombre VARCHAR(255) NOT NULL,
	apellido VARCHAR(255) NOT NULL,
	nickname VARCHAR(255) NOT NULL,
	pasword VARCHAR(255) NOT NULL,
	permiso VARCHAR(255) NOT NULL,
	PRIMARY KEY(idUsuario)
)
select * from Usuario
CREATE TABLE DetalleModulo(
	idModulo INT NOT NULL,
	idUsuario INT NOT NULL,
	PRIMARY KEY(idModulo, idUsuario),
)


CREATE TABLE Profesor(
	idProfesor INT IDENTITY(1,1),
	nombre VARCHAR(255) NOT NULL,
	clase VARCHAR(255) NOT NULL,
	PRIMARY KEY(idProfesor)
)


CREATE TABLE Prestamo(
	idPrestamo INT IDENTITY(1,1),
	hora VARCHAR(255) NOT NULL,
	jornada VARCHAR(255) NOT NULL,
	fecha VARCHAR(255) NOT NULL,
	fechaDevolucion VARCHAR(255) NOT NULL,
	estadoPrestamo BIT DEFAULT 0,
	idProfesor INT NOT NULL,
	idUsuario INT NOT NULL,
	PRIMARY KEY(idPrestamo),
	FOREIGN KEY(idProfesor) REFERENCES Profesor(idProfesor),
	FOREIGN KEY(idUsuario) REFERENCES Usuario(idUsuario)
)


CREATE TABLE Categoria(
	idCategoria INT IDENTITY(1,1),
	nombre VARCHAR(255) NOT NULL,
	PRIMARY KEY(idCategoria)
)

CREATE TABLE Tipo(
	idTipo INT IDENTITY(1,1),
	nombre	VARCHAR(255) NOT NULL,
	idCategoria INT NOT NULL,
	cantidad INT DEFAULT 0,
	cantidadDisponible INT DEFAULT 0,
	PRIMARY KEY(idTipo),
	FOREIGN KEY(idCategoria) REFERENCES Categoria(idCategoria)
)

CREATE TABLE Herramienta(
	idHerramienta INT IDENTITY(1,1),
	serial VARCHAR(255) NOT NULL,
	idTipo INT NOT NULL,
	estado BIT DEFAULT 0,
	PRIMARY KEY(idHerramienta),
	FOREIGN KEY(idTipo) REFERENCES Tipo(idTipo)
)



CREATE TABLE DetallePrestamo(
	idHerramienta INT NOT NULL,
	idPrestamo INT NOT NULL,
	PRIMARY KEY(idHerramienta, idPrestamo),
	FOREIGN KEY(idHerramienta) REFERENCES Herramienta(idHerramienta),
	FOREIGN KEY(idPrestamo) REFERENCES Prestamo(idPrestamo)
)

CREATE TABLE Devolucion(
	idDevolucion INT NOT NULL,
	fecha VARCHAR(255) NOT NULL,
	idUsuario INT NOT NULL,
	idProfesor INT NOT NULL,
	PRIMARY KEY(idDevolucion),
	FOREIGN KEY(idUsuario) REFERENCES Usuario(idUsuario),
	FOREIGN KEY(idProfesor) REFERENCES Profesor(idProfesor)
)

CREATE TABLE DetalleDevolucion(
	idHerramienta INT NOT NULL,
	idDevolucion INT NOT NULL,
	PRIMARY KEY(idHerramienta, idDevolucion),
	FOREIGN KEY(idHerramienta) REFERENCES Herramienta(idHerramienta),
	FOREIGN KEY(idDevolucion) REFERENCES Devolucion(idDevolucion)
)

--Creacion de procedimientos de usuario
--Verificar
GO
CREATE PROCEDURE VerificarUsuario @nickname VARCHAR(255), @password VARCHAR(255)
AS
BEGIN
	SELECT Usuario.idUsuario, Usuario.pasword, Usuario.nickname, Usuario.nombre, Usuario.apellido, Usuario.permiso
	FROM Usuario WHERE Usuario.nickname=@nickname AND Usuario.pasword=@password
END

select * from Usuario
--Agregar
GO
CREATE PROCEDURE InsertarUsuario @nombre VARCHAR(255), @apellido VARCHAR(255), @nickname VARCHAR(255), @pasword VARCHAR(255), @permiso VARCHAR(255)
AS 
BEGIN
INSERT INTO Usuario(nombre, apellido, nickname, pasword,permiso) VALUES (@nombre, @apellido, @nickname,@pasword, @permiso)
END

--Eliminar
GO
CREATE PROCEDURE eliminarUsuario @idUsuario INT
AS
BEGIN
	DECLARE @idPrestamo INT
	DECLARE cPrestamos CURSOR
	FOR SELECT Prestamo.idPrestamo FROM Prestamo WHERE Prestamo.idUsuario=@idUsuario
	OPEN cPrestamos
	FETCH cPrestamos INTO @idPrestamo
	WHILE(@@FETCH_STATUS=0)
	BEGIN
		DELETE FROM DetallePrestamo WHERE DetallePrestamo.idPrestamo=@idPrestamo
		DELETE FROM Prestamo WHERE Prestamo.idUsuario=@idUsuario
		FETCH cPrestamos INTO @idPrestamo	
	END
	CLOSE cPrestamos
	DEALLOCATE cPrestamos
	DELETE FROM Usuario WHERE Usuario.idUsuario=@idUsuario
END

SELECT * from usuario


--Modificar valores individualmente
--NOMBRE
GO
CREATE PROCEDURE modificarNombreUsuario @nuevoNombre VARCHAR(255),@idUsuario INT
AS
BEGIN
UPDATE Usuario SET nombre=@nuevoNombre WHERE idUsuario=@idUsuario
END

--APELLIDO
GO
CREATE PROCEDURE modificarApellidoUsuario @nuevoApellido VARCHAR(255), @idUsuario INT
AS
BEGIN
UPDATE Usuario SET apellido=@nuevoApellido WHERE idUsuario=@idUsuario
END





--NICKNAME
GO
CREATE PROCEDURE modificarNicknameUsuario @nuevoNickname VARCHAR(255),@idUsuario INT
AS
BEGIN
UPDATE Usuario SET nickname=@nuevoNickname WHERE idUsuario=@idUsuario
END

--PASWORD
GO
CREATE PROCEDURE modificarPaswordUsuario @nuevoPasword VARCHAR(255),@idUsuario INT
AS
BEGIN
UPDATE Usuario SET pasword=@nuevoPasword WHERE idUsuario=@idUsuario
END


--Modificaciones de Categorias
--NOMBRE
GO
CREATE PROCEDURE modificarNombreCategoria @nuevoNombre VARCHAR(255),@idCategoria INT
AS
BEGIN
UPDATE Categoria SET nombre=@nuevoNombre WHERE idCategoria=@idCategoria
END

--Agregar
GO
CREATE PROCEDURE InsertarCategoria @nombre VARCHAR(255)
AS 
BEGIN
INSERT INTO Categoria(nombre) VALUES (@nombre)
END

--Profesor
GO
CREATE PROCEDURE insertarProfesor @nombre VARCHAR(255), @clase VARCHAR(255)
AS 
BEGIN
INSERT INTO Profesor(nombre, clase) VALUES (@nombre, @clase)
END

--NOMBRE PROFESOR
GO
CREATE PROCEDURE modificarNombreProfesor @nuevoNombre VARCHAR(255),@idProfesor INT
AS
BEGIN
UPDATE Profesor SET nombre=@nuevoNombre WHERE idProfesor=@idProfesor
END

--CLASE PROFESOR
GO
CREATE PROCEDURE modificarClaseProfesor @nuevoNombre VARCHAR(255),@idProfesor INT
AS
BEGIN
UPDATE Profesor SET clase=@nuevoNombre WHERE idProfesor=@idProfesor
END


--Tipo
GO
CREATE PROCEDURE insertarTipo @nombre VARCHAR(255), @idCategoria INT
AS 
BEGIN
INSERT INTO Tipo(nombre, idCategoria) VALUES (@nombre, @idCategoria)
END

--Modificar Nombre tipos
GO
CREATE PROCEDURE modificarNombreTipo @nuevoNombre VARCHAR(255),@idTipo INT
AS
BEGIN

UPDATE Tipo SET nombre=@nuevoNombre WHERE idTipo=@idTipo
END

--Modificar Categoria tipos
GO
CREATE PROCEDURE modificarCategoriaTipo @nuevoCategoria	INT,@idTipo INT
AS
BEGIN
UPDATE Tipo SET idCategoria=@nuevoCategoria WHERE idTipo=@idTipo
END


--Modificar Serial en Herramienta

GO
CREATE PROCEDURE modificarSerialHerramienta @nuevoSerial VARCHAR(255),@idHerramienta INT
AS
BEGIN
UPDATE Herramienta SET serial=@nuevoSerial WHERE idHerramienta=@idHerramienta
END


Select Herramienta.idHerramienta, Herramienta.serial, Tipo.nombre as tipo FROM Herramienta INNER JOIN Tipo ON Herramienta.idTipo = Tipo.idTipo


--AgregarHerramienta
GO
CREATE PROCEDURE agregarHerramienta @serial VARCHAR(255),@idTipo INT
AS
BEGIN
	INSERT INTO Herramienta(serial, idTipo) VALUES (@Serial,@idTipo)
END

--Editar tipo en herramienta
GO
CREATE PROCEDURE modificarTipoHerramienta @tipo INT, @herramientaId INT
AS
BEGIN
UPDATE Herramienta SET idTipo=@tipo WHERE idHerramienta=@herramientaId
END


--Eliminar Categoria
drop procedure eliminarCategoria
GO
CREATE PROCEDURE eliminarCategoria @categoria INT
AS
BEGIN
	DECLARE @idTipo INT
	DECLARE cTipo CURSOR
		FOR SELECT idTipo FROM Tipo WHERE idCategoria=@categoria
		OPEN cTipo 
		FETCH cTipo INTO @idTipo
		WHILE(@@FETCH_STATUS=0)
		BEGIN
			DECLARE @idHerramienta INT 
			DECLARE cHerramienta CURSOR
			FOR SELECT idHerramienta FROM Herramienta WHERE idTipo=@idTipo  
			OPEN cHerramienta 
			FETCH cHerramienta INTO @idHerramienta
			WHILE(@@FETCH_STATUS=0)
			BEGIN
				DECLARE @prestamo INT
				DECLARE cPrestamo CURSOR
				FOR SELECT idPrestamo FROM DetallePrestamo WHERE idHerramienta=@idHerramienta
				OPEN cPrestamo
				FETCH cPrestamo INTO @prestamo
				WHILE(@@FETCH_STATUS=0)
				BEGIN
					DELETE FROM DetallePrestamo WHERE idPrestamo=@prestamo
					DELETE FROM Prestamo WHERE idPrestamo=@prestamo
					FETCH cPrestamo INTO @prestamo
				END
				CLOSE cPrestamo
				DEALLOCATE cPrestamo
				DELETE FROM Herramienta WHERE idHerramienta=@idHerramienta
				FETCH cHerramienta INTO @idHerramienta
			END
			CLOSE cHerramienta
			DEALLOCATE cHerramienta
			DELETE Tipo FROM Tipo WHERE idTipo=@idTipo
			FETCH cTipo INTO @idTipo
		END
		CLOSE cTipo
		DEALLOCATE cTipo
		DELETE Categoria FROM Categoria WHERE idCategoria=@categoria
END


--Eliminar Profesor
GO
CREATE PROCEDURE eliminarProfesor @idProfesor INT
AS
BEGIN
	DECLARE @idPrestamo INT
	DECLARE cPrestamos CURSOR
	FOR SELECT Prestamo.idPrestamo FROM Prestamo WHERE Prestamo.idProfesor=@idProfesor
	OPEN cPrestamos
	FETCH cPrestamos INTO @idPrestamo
	WHILE(@@FETCH_STATUS=0)
	BEGIN
		DELETE FROM DetallePrestamo WHERE DetallePrestamo.idPrestamo=@idPrestamo
		DELETE FROM Prestamo WHERE Prestamo.idProfesor=@idProfesor
		FETCH cPrestamos INTO @idPrestamo	
	END
	CLOSE cPrestamos
	DEALLOCATE cPrestamos
	DELETE FROM Profesor WHERE Profesor.idProfesor=@idProfesor
END


--Eliminar Tipo
GO
CREATE PROCEDURE eliminarTipo @idTipo INT
AS
BEGIN
	DECLARE @idHerramienta INT
	DECLARE cHerramienta CURSOR
	FOR SELECT idHerramienta FROM Herramienta WHERE Herramienta.idTipo=@idTipo
	OPEN cHerramienta
	FETCH cHerramienta INTO @idHerramienta
	WHILE(@@FETCH_STATUS=0)
	BEGIN
		DECLARE @prestamo INT
		DECLARE cPrestamo CURSOR
		FOR SELECT idPrestamo FROM DetallePrestamo WHERE idHerramienta=@idHerramienta
		OPEN cPrestamo
		FETCH cPrestamo INTO @prestamo
		WHILE(@@FETCH_STATUS=0)
		BEGIN
			DELETE FROM DetallePrestamo WHERE idPrestamo=@prestamo
			DELETE FROM Prestamo WHERE idPrestamo=@prestamo
			FETCH cPrestamo INTO @prestamo
		END
		CLOSE cPrestamo
		DEALLOCATE cPrestamo
		DELETE FROM Herramienta WHERE idHerramienta=@idHerramienta
		FETCH cHerramienta INTO @idHerramienta
	END
	CLOSE cHerramienta
	DEALLOCATE cHerramienta
	DELETE FROM Tipo WHERE Tipo.idTipo=@idTipo
END

--Eliminar Herramienta
GO
CREATE PROCEDURE eliminarHerramienta @idHerramienta INT
AS
BEGIN
	DECLARE @prestamo INT
	DECLARE cPrestamo CURSOR
	FOR SELECT idPrestamo FROM DetallePrestamo WHERE idHerramienta=@idHerramienta
	OPEN cPrestamo
	FETCH cPrestamo INTO @prestamo
	WHILE(@@FETCH_STATUS=0)
	BEGIN
		DELETE FROM DetallePrestamo WHERE idPrestamo=@prestamo
		DELETE FROM Prestamo WHERE idPrestamo=@prestamo
		FETCH cPrestamo INTO @prestamo
	END
	CLOSE cPrestamo
	DEALLOCATE cPrestamo
	DELETE FROM Herramienta WHERE idHerramienta=@idHerramienta;
END

--Pretamo
GO
CREATE PROCEDURE insertarPrestamo @fecha VARCHAR(255), @devolucion VARCHAR(255) ,@hora VARCHAR(255), @jornada VARCHAR(255),@idProfesor INT, @idUsuario INT
AS
BEGIN
	INSERT INTO Prestamo(hora,jornada,fecha,fechaDevolucion,idProfesor, idUsuario) VALUES (@hora,@jornada,@fecha,@devolucion, @idProfesor,@idUsuario)
END

--InsertarEnElDetall
GO
CREATE PROCEDURE insertarDetallePrestamo @idPrestamo INT, @idHerramienta INT
AS
BEGIN
	INSERT INTO DetallePrestamo(idPrestamo, idHerramienta) VALUES (@idPrestamo, @idHerramienta)
END

--Retornar id por nombre

select idProfesor FROM Profesor Where nombre='mayen'

GO 
CREATE TRIGGER aumentarCantidad
ON Herramienta
AFTER INSERT
AS
BEGIN
	DECLARE @idTipo INT
	SET @idTipo =(SELECT idTipo FROM inserted)
	UPDATE Tipo SET cantidad=(cantidad+1) WHERE idTipo=@idTipo
	UPDATE Tipo SET cantidadDisponible=(cantidadDisponible+1) WHERE idTipo=@idTipo
END



GO 
CREATE TRIGGER cambiarEstadoHerramienta
ON DetallePrestamo
AFTER INSERT
AS
BEGIN
	DECLARE @idHerramienta INT
	DECLARE @idTipo INT
	SET @idHerramienta =(SELECT idHerramienta FROM inserted)
	SET @idTipo=(SELECT Tipo.idTipo FROM inserted INNER JOIN Herramienta ON inserted.idHerramienta=Herramienta.idHerramienta INNER JOIN Tipo ON Herramienta.idTipo=Tipo.idTipo WHERE Herramienta.idHerramienta=@idHerramienta)
	print @idTipo
	UPDATE Herramienta SET estado=1 WHERE idHerramienta=@idHerramienta
	UPDATE Tipo SET cantidadDisponible=(cantidadDisponible-1) WHERE idTipo=@idTipo
END

GO
CREATE PROCEDURE cambiarEstadoPrestamo @idPrestamo INT
AS
BEGIN
	DECLARE @herramienta INT
	DECLARE cHerramienta CURSOR
	FOR SELECT idHerramienta FROM DetallePrestamo WHERE idPrestamo=@idPrestamo
	OPEN cHerramienta
	FETCH cHerramienta INTO @herramienta
	WHILE(@@FETCH_STATUS=0)
	BEGIN
		DECLARE @tipo INT
		DECLARE @cantidad INT
		SET @tipo =(SELECT Herramienta.idTipo FROM Herramienta WHERE idHerramienta=@herramienta)
		print @tipo
		SET @cantidad =(SELECT Tipo.cantidadDisponible FROM Tipo WHERE idTipo=@tipo)
		print @cantidad
		select * from Tipo
		UPDATE Tipo SET cantidadDisponible=(cantidadDisponible+1) WHERE idTipo=@tipo
		SET @cantidad =(SELECT Tipo.cantidadDisponible FROM Tipo WHERE idTipo=@tipo)
		print @cantidad
		select * from Tipo
		UPDATE Herramienta SET estado=0 WHERE idHerramienta=@herramienta
		FETCH cHerramienta INTO @herramienta	
	END
	CLOSE cHerramienta
	DEALLOCATE cHerramienta
	UPDATE Prestamo SET estadoPrestamo=1 WHERE Prestamo.idPrestamo=@idPrestamo	 
END


GO
CREATE PROCEDURE actualizarCantidadesFlotantes
AS
BEGIN
	DECLARE @tipo INT
	DECLARE cTipo CURSOR
	FOR SELECT idTipo FROM Herramienta 
	OPEN cTipo
	FETCH cTipo INTO @tipo
	WHILE(@@FETCH_STATUS=0)	
	BEGIN
		UPDATE Tipo SET cantidad=(cantidad+1) WHERE idTipo=@tipo
		FETCH cTipo INTO @tipo
	END
	CLOSE cTipo
	DEALLOCATE cTipo
END



GO
CREATE PROCEDURE volverTodasCero
AS
BEGIN
	DECLARE @idTipo INT
	DECLARE cTipo CURSOR
	FOR SELECT idTipo FROM Herramienta 
	OPEN cTipo
	FETCH cTipo INTO @idTipo
	WHILE(@@FETCH_STATUS=0)	
	BEGIN
		UPDATE Tipo SET cantidad=0 WHERE Tipo.idTipo=@idTipo
		FETCH cTipo INTO @idTipo
	END
	CLOSE cTipo
	DEALLOCATE cTipo
END
select * from Tipo
select * from Herramienta
exec volverTodasCero
exec actualizarCantidadesFlotantes





--exec cambiarEstadoPrestamo 2

select * from Herramienta
select * from Tipo
select * from Prestamo


GO
EXEC InsertarUsuario 'Daniel', 'Mendez', 'izzy', 'Mother', 'crud-visor-prestamista'
EXEC InsertarUsuario 'Jorge', 'Pineda', 'jorg', '1', 'visor-prestamista'
EXEC InsertarUsuario 'Diego', 'Alejandro', 'dor', '2','visor'

GO
EXEC insertarProfesor 'mayen', 'PE5DM'
EXEC insertarProfesor 'cesar', 'PE4BM'
GO
Exec InsertarCategoria 'tics'
EXEC InsertarCategoria 'mecanica'
EXEC InsertarCategoria 'hidraulico'
GO
EXEC insertarTipo 'computadora hp', 1
EXEC insertarTipo 'audifonos',1
GO
select * from Tipo
select * from Herramienta
EXEC agregarHerramienta 'CDK1KJ', 1
EXEC agregarHerramienta 'AudioXPI1', 1
EXEC agregarHerramienta '3244324',2
GO
EXEC insertarPrestamo '03/06/2014','05/07/2014','3:57','Matutina',1,1
GO
EXEC insertarDetallePrestamo 1,1
EXEC insertarDetallePrestamo 1,2

--EXEC cambiarEstadoPrestamo 2
select * from Tipo
select * from Herramienta
select * from Usuario
select * from Categoria
select * from Tipo
select * from Prestamo
select * from DetallePrestamo
select * from Profesor

