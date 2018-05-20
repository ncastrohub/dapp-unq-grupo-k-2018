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

@NgModule({
  declarations: [
    AppComponent,
    ListComponent,
    EditVehicleComponent,
    CreateVehicleComponent,
  ],
  imports: [
    BrowserModule,
    FormsModule,
    AppRoutingModule,
    HttpClientModule
  ],
  providers: [VehicleService, AppConfig, { provide: LOCALE_ID, useValue: 'en' }],
  bootstrap: [AppComponent]
})
export class AppModule { }
