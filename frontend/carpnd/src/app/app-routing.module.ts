import { NgModule }             from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { ListComponent } from './vehicle/list/list.component';
import { EditVehicleComponent } from './vehicle/edit/edit.component';
import { CreateVehicleComponent } from './vehicle/create/create.component';
import { UsereditComponent } from './user/useredit/useredit.component';
import { UserDetailComponent } from './user/user-detail/user-detail.component';
import { AuthGuard } from './auth/auth.guard';
import { NotAuthGuard } from './auth/not-auth.guard';
import { CallbackComponent } from './callback.component';
import { PublicationListComponent } from './publication/publication-list/publication-list.component';
import { PublicationCreateComponent } from './publication/publication-create/publication-create.component';
import { GooglemapsComponent } from './maps/googlemaps/googlemaps.component';
import { DetailComponent } from './publication/detail/detail.component';
import { ReservationDetailComponent } from './reservation/reservation-detail/reservation-detail.component';
import { HomeComponent } from './home/home/home.component';
import { UsernotexistsComponent } from './home/usernotexists/usernotexists.component';
import { CreditComponent } from './credit/credit/credit.component';
import { ReservationListComponent } from './reservation/reservation-list/reservation-list.component';
const routes: Routes = [

  { path: '', component: HomeComponent },
  { path: 'user-not-exists', component: UsernotexistsComponent },
  { path: 'vehicle/create', component: CreateVehicleComponent, canActivate: [AuthGuard] },
  { path: 'vehicle/edit', component: EditVehicleComponent, canActivate: [AuthGuard]},
  { path: 'vehicle/list', component: ListComponent, canActivate: [AuthGuard] },
  { path: 'user/edit', component: UsereditComponent, canActivate: [AuthGuard] },
  { path: 'user/detail', component: UserDetailComponent, canActivate: [AuthGuard]},
  { path: 'user/add-money', component: CreditComponent, canActivate: [AuthGuard]},
  { path: 'publication/list', component: PublicationListComponent, canActivate: [AuthGuard]  },
  // { path: 'publication/list', component: PublicationListComponent, canActivate: [AuthGuard]  },
  { path: 'publication/create', component: PublicationCreateComponent, canActivate: [AuthGuard]  },
  { path: 'publication/detail/:publicationId', component: DetailComponent, canActivate: [AuthGuard]  },
  { path: 'reservation/detail/:reservationId', component: ReservationDetailComponent, canActivate: [AuthGuard]  },
  { path: 'reservation/list/owner', component: ReservationListComponent, canActivate: [AuthGuard]  },
  { path: 'reservation/list/customer', component: ReservationListComponent, canActivate: [AuthGuard]  },
  { path: 'callback/:authorization_role', component: CallbackComponent, canActivate: [NotAuthGuard] },

];

@NgModule({
  exports: [ RouterModule ],
  imports: [ RouterModule.forRoot(routes) ]
  , providers: [AuthGuard, NotAuthGuard]
})

export class AppRoutingModule {}
