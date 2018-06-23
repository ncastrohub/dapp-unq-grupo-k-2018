import { NgModule }             from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { ListComponent } from './vehicle/list/list.component';
import { EditVehicleComponent } from './vehicle/edit/edit.component';
import { CreateVehicleComponent } from './vehicle/create/create.component';
import { UserListComponent } from './user/user-list/user-list.component';
import { CreateuserComponent } from './user/createuser/createuser.component';
import { UsereditComponent } from './user/useredit/useredit.component';
import { AuthGuard } from './auth/auth.guard';
import { NotAuthGuard } from './auth/not-auth.guard';
import { CallbackComponent } from './callback.component';
import { PublicationListComponent } from './publication/publication-list/publication-list.component';
import { PublicationCreateComponent } from './publication/publication-create/publication-create.component';
import { GooglemapsComponent } from './maps/googlemaps/googlemaps.component';
import { DetailComponent } from './publication/detail/detail.component';
import { ReservationDetailComponent } from './reservation/reservation-detail/reservation-detail.component';

const routes: Routes = [
  { path: 'vehicle/create', component: CreateVehicleComponent },
  { path: 'vehicle/edit', component: EditVehicleComponent},
  { path: 'vehicle/list', component: ListComponent, canActivate: [AuthGuard] },
  { path: 'user/edit', component: UsereditComponent, canActivate: [AuthGuard] },
  { path: 'user/create', component: CreateuserComponent, canActivate: [AuthGuard]},
  { path: 'publication/list', component: PublicationListComponent, canActivate: [AuthGuard]  },
  { path: 'publication/create', component: PublicationCreateComponent, canActivate: [AuthGuard]  },
  { path: 'publication/detail/:publicationId', component: DetailComponent, canActivate: [AuthGuard]  },
  { path: 'reservation/detail/:reservationId', component: ReservationDetailComponent, canActivate: [AuthGuard]  },
  { path: 'callback', component: CallbackComponent, canActivate: [NotAuthGuard] },
];

@NgModule({
  exports: [ RouterModule ],
  imports: [ RouterModule.forRoot(routes) ]
  , providers: [AuthGuard, NotAuthGuard]
})

export class AppRoutingModule {}
