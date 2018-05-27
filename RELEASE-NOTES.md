Carpnd 
Release Notes


1.0.0

NEW FEATURES:

* Se puede crear una reserva
* Se puede cargar un vehiculo
* La reserva tiene un flujo de estados que determinan
en que momento del proceso esta
* Se puede cargar un usuario
* Se puede pagar con un tipo de moneda y un monto
* Se calcula que el dinero en la cuenta de quien paga
este disponible
* La reserva puede hacerse hasta por 5 dias
* Se puede realizar una publicacion y se puede definir
que dias no esta disponible la misma

CHANGES:

* Primera Version.


BUG FIXES:

* No

KNOWN ISSUES:

* No se notifica a las partes via mail cualquier cambio en la reserva
* No esta realizada la funcionalidad de crear la reserva desde un usuario
* La cobertura de test es del 70% la cual deberia ser mayor
* No esta realizada la funcionalidad de devolver el dinero al cliente si este
cancela la reserva
* No se notifica una vez por mes a los usuarios su estado de cuenta
* Si bien el dinero se reserva en la cuenta, falta realizar el final de reserva
y que el dinero se agregue a la cuenta del duenio del vehiculo
* En caso de interrumpirse antes de tiempo la reserva, es necesario liberar los dias
que queden restantes que estan reservados
* NO HAY FUNCIONALIDAD DE FRONTEND


1.0.1

NEW FEATURES:

* AMB Usuarios
* AMB Vehiculos
* Deploy automatico del backend
* Reporte de Covertura de test automatica
* I18N funcionando

CHANGES

* No

KNOWN ISSUES:

* Deploy automatico del frontend no esta funcionando