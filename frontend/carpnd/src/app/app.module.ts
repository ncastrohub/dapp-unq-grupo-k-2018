import { BrowserModule } from '@angular/platform-browser';
import {  LOCALE_ID, NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import { FormsModule, ReactiveFormsModule  } from '@angular/forms';

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
import { PublicationCreateComponent } from './publication/publication-create/publication-create.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { DateSelectorComponent } from './publication/date-selector/date-selector.component';
import {MomentModule} from 'angular2-moment/moment.module';
import { CostDefinerComponent } from './publication/cost-definer/cost-definer.component';
import {ReservationService} from './reservation/reservation.service';


// AGREGADO PARA AUTENTICACION
import {AuthService} from "./auth/auth.service";
import { CallbackComponent } from './callback.component';
import { DetailComponent } from './publication/detail/detail.component';
import { ReservationListComponent } from './reservation/reservation-list/reservation-list.component';
import { VehicleListComponent } from './vehicle/vehicle-list/vehicle-list.component';
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
    GooglemapsComponent,
    PublicationListComponent,
    PublicationCreateComponent,
    DateSelectorComponent,
    CostDefinerComponent,
    DetailComponent,
    ReservationListComponent,
    VehicleListComponent,
  ],
  imports: [
    MomentModule,
    BrowserModule,
    FormsModule,
    ReactiveFormsModule,
    AppRoutingModule,
    HttpClientModule,
    AgmCoreModule.forRoot({
      libraries: ["places"],
      apiKey: 'AIzaSyApkqv2wRZywgM-wVDJmceHuBROKB1gvOQ'
    }),
    NgbModule.forRoot()
  ],
  providers: [VehicleService,
  // AGREGADO PARA AUTENTICACION
      AuthService,
  // FIN AGREGADO
  ReservationService,
    UserServiceService,
    AppConfig,
    PublicationService,
    { provide: LOCALE_ID, useValue: 'en' }],
  bootstrap: [AppComponent]
})
export class AppModule { }
