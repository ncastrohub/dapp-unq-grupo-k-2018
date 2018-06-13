import { NgModule }             from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { ListComponent } from './vehicle/list/list.component';
import { EditVehicleComponent } from './vehicle/edit/edit.component';
import { CreateVehicleComponent } from './vehicle/create/create.component';
import { UserListComponent } from './user/user-list/user-list.component'
import { CreateuserComponent } from './user/createuser/createuser.component'
import { UsereditComponent } from './user/useredit/useredit.component'
// AGREGADO PARA AUTENTICACION
import { AuthGuard } from './auth/auth.guard';
import { CallbackComponent } from './callback.component';
// FIN AGREGADO
import { PublicationListComponent } from './publication/publication-list/publication-list.component'
import { PublicationCreateComponent } from './publication/publication-create/publication-create.component';
import { GooglemapsComponent } from './maps/googlemaps/googlemaps.component';

const routes: Routes = [
  { path: 'vehicle/create', component: CreateVehicleComponent },
  { path: 'vehicle/edit', component: EditVehicleComponent},
  { path: 'vehicle/list', component: ListComponent },
  { path: 'user/list', component: UserListComponent },
  { path: 'user/edit', component: UsereditComponent },
  { path: 'user/create', component: CreateuserComponent
// AGREGADO PARA AUTENTICACION
// DESCOMENTAR PARA GUARDAR UNA RUTA
//    ,  canActivate: [AuthGuard]
// FIN AGREGADO
  },
// AGREGADO PARA AUTENTICACION
  { path: 'callback', component: CallbackComponent },
// FIN AGREGADO
  { path: 'publication/list', component: CreateuserComponent },
  { path: 'publication/create', component: PublicationCreateComponent },
  { path: 'publication/locations', component: GooglemapsComponent },
];

@NgModule({
  exports: [ RouterModule ],
  imports: [ RouterModule.forRoot(routes) ]
// AGREGADO PARA AUTENTICACION
  , providers: [AuthGuard]
// FIN AGREGADO
})

export class AppRoutingModule {}
