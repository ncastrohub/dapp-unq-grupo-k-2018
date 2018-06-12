import { BrowserModule } from '@angular/platform-browser';
import {  LOCALE_ID, NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import { FormsModule } from '@angular/forms';

import { AppRoutingModule }     from './app-routing.module';
import { HttpClientModule } from '@angular/common/http';
import { ListComponent } from './vehicle/list/list.component';
import { VehicleService } from './vehicle/service/vehicle.service';
import { AppConfig } from './config';
import { EditVehicleComponent } from './vehicle/edit/edit.component';
import { CreateVehicleComponent } from './vehicle/create/create.component';
import { CreateuserComponent } from './user/createuser/createuser.component';
import { UserListComponent } from './user/user-list/user-list.component';
import { UserServiceService } from './user/service/user-service.service';
import { UsereditComponent } from './user/useredit/useredit.component';

// AGREGADO PARA AUTENTICACION
import {AuthService} from "./auth/auth.service";
import { CallbackComponent } from './callback.component';
// FIN AGREGADO

@NgModule({
  declarations: [
    AppComponent,
    ListComponent,
    EditVehicleComponent,
    CreateVehicleComponent,
    CreateuserComponent,
    UserListComponent,
    UsereditComponent,
    CallbackComponent,
  ],
  imports: [
    BrowserModule,
    FormsModule,
    AppRoutingModule,
    HttpClientModule
  ],
  providers: [VehicleService, UserServiceService, AppConfig,
// AGREGADO PARA AUTENTICACION
    AuthService,
// FIN AGREGADO
    { provide: LOCALE_ID, useValue: 'en' }],
  bootstrap: [AppComponent]
})
export class AppModule { }
