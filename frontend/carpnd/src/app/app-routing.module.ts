import { NgModule }             from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { ListComponent } from './vehicle/list/list.component';
import { EditVehicleComponent } from './vehicle/edit/edit.component';
import { CreateVehicleComponent } from './vehicle/create/create.component';
import { UserListComponent } from './user/user-list/user-list.component'
import { CreateuserComponent } from './user/createuser/createuser.component'
import { UsereditComponent } from './user/useredit/useredit.component'
import { PublicationListComponent } from './publication/publication-list/publication-list.component'
import { PublicationCreateComponent } from './publication/publication-create/publication-create.component';

const routes: Routes = [
  { path: 'vehicle/create', component: CreateVehicleComponent },
  { path: 'vehicle/edit', component: EditVehicleComponent},
  { path: 'vehicle/list', component: ListComponent },
  { path: 'user/list', component: UserListComponent },
  { path: 'user/edit', component: UsereditComponent },
  { path: 'user/create', component: CreateuserComponent },
  { path: 'publication/list', component: CreateuserComponent },
  { path: 'publication/create', component: PublicationCreateComponent },

];

@NgModule({
  exports: [ RouterModule ],
  imports: [ RouterModule.forRoot(routes) ]
})

export class AppRoutingModule {}