ALTER TABLE Venta 
DROP COLUMN isPrestamo;
    
ALTER TABLE Venta
DROP COLUMN isReserva;
    
ALTER TABLE Venta
ADD COLUMN tipoVenta int;
	