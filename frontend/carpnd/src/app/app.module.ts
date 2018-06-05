import { BrowserModule } from '@angular/platform-browser';
import {  LOCALE_ID, NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import { FormsModule } from '@angular/forms';

import { AgmCoreModule } from '@agm/core';

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
import { GooglemapsComponent } from './maps/googlemaps/googlemaps.component';
import { PublicationListComponent } from './publication/publication-list/publication-list.component';
import { PublicationService } from './publication/publication.service';

@NgModule({
  declarations: [
    AppComponent,
    ListComponent,
    EditVehicleComponent,
    CreateVehicleComponent,
    CreateuserComponent,
    UserListComponent,
    UsereditComponent,
    GooglemapsComponent,
    PublicationListComponent,
  ],
  imports: [
    BrowserModule,
    FormsModule,
    AppRoutingModule,
    HttpClientModule,
    AgmCoreModule.forRoot({
      apiKey: 'AIzaSyApkqv2wRZywgM-wVDJmceHuBROKB1gvOQ'
    })
  ],
  providers: [VehicleService,
    UserServiceService,
    AppConfig,
    PublicationService,
    { provide: LOCALE_ID, useValue: 'en' }],
  bootstrap: [AppComponent]
})
export class AppModule { }
