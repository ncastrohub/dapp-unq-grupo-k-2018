import { BrowserModule } from '@angular/platform-browser';
import {  LOCALE_ID, NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import { FormsModule, ReactiveFormsModule  } from '@angular/forms';

import { AgmCoreModule } from '@agm/core';

import { AppRoutingModule }     from './app-routing.module';
import { HttpClientModule, HttpClient } from '@angular/common/http';
import { ListComponent } from './vehicle/list/list.component';
import { VehicleService } from './vehicle/service/vehicle.service';
import { AppConfig } from './config';
import { EditVehicleComponent } from './vehicle/edit/edit.component';
import { CreateVehicleComponent } from './vehicle/create/create.component';
import { UserServiceService } from './user/service/user-service.service';
import { UsereditComponent } from './user/useredit/useredit.component';
import { GooglemapsComponent } from './maps/googlemaps/googlemaps.component';
import { PublicationListComponent } from './publication/publication-list/publication-list.component';
import { PublicationService } from './publication/publication.service';
import { PublicationCreateComponent } from './publication/publication-create/publication-create.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { DateSelectorComponent } from './publication/date-selector/date-selector.component';
import { CostDefinerComponent } from './publication/cost-definer/cost-definer.component';
import { ReservationService } from './reservation/reservation.service';
import { DetailComponent } from './publication/detail/detail.component';
import { VehicleListComponent } from './vehicle/vehicle-list/vehicle-list.component';
import { ReservationDetailComponent } from './reservation/reservation-detail/reservation-detail.component';


import { AuthService } from "./auth/auth.service";
import { CallbackComponent } from './callback.component';

import { HTTP_INTERCEPTORS } from '@angular/common/http';
import { TokenInterceptor } from './auth/token.bearer.interceptor';
import { UserDetailComponent } from './user/user-detail/user-detail.component';
import { HomeComponent } from './home/home/home.component';
import { UsernotexistsComponent } from './home/usernotexists/usernotexists.component';
import { LoadingComponent } from './loading/loading/loading.component';
import { CreditComponent } from './credit/credit/credit.component';
import { CreditService } from './credit/credit.service';
import { ReservationListComponent } from './reservation/reservation-list/reservation-list.component';
import {TranslateModule, TranslateLoader} from '@ngx-translate/core';


import {TranslateHttpLoader} from '@ngx-translate/http-loader';

// AoT requires an exported function for factories
export function HttpLoaderFactory(http: HttpClient) {
    return new TranslateHttpLoader(http);
}


@NgModule({
  declarations: [
    AppComponent,
    ListComponent,
    EditVehicleComponent,
    CreateVehicleComponent,
    UsereditComponent,
    CallbackComponent,
    GooglemapsComponent,
    PublicationListComponent,
    PublicationCreateComponent,
    DateSelectorComponent,
    CostDefinerComponent,
    DetailComponent,
    VehicleListComponent,
    ReservationDetailComponent,
    UserDetailComponent,
    HomeComponent,
    UsernotexistsComponent,
    LoadingComponent,
    CreditComponent,
    ReservationListComponent,
  ],
  imports: [
    BrowserModule,
    FormsModule,
    ReactiveFormsModule,
    AppRoutingModule,
    HttpClientModule,
    TranslateModule.forRoot({
            loader: {
                provide: TranslateLoader,
                useFactory: HttpLoaderFactory,
                deps: [HttpClient]
            }
        }),
    AgmCoreModule.forRoot({
      libraries: ["places"],
      apiKey: 'AIzaSyApkqv2wRZywgM-wVDJmceHuBROKB1gvOQ'
    }),
    NgbModule.forRoot()
  ],
  providers: [
    {
      provide: HTTP_INTERCEPTORS,
      useClass: TokenInterceptor,
      multi: true
    },
    { provide: LOCALE_ID, useValue: 'es' },
    CreditService,
    VehicleService,
    AuthService,
    ReservationService,
    UserServiceService,
    AppConfig,
    PublicationService],
    bootstrap: [AppComponent]
})




export class AppModule { }
