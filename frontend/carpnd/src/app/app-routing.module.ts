import { NgModule }             from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { ListComponent } from './vehicle/list/list.component';
import { EditVehicleComponent } from './vehicle/edit/edit.component';
import { CreateVehicleComponent } from './vehicle/create/create.component';
import { UserListComponent } from './user/user-list/user-list.component'
 

const routes: Routes = [
  { path: 'vehicle/create', component: CreateVehicleComponent },
  { path: 'vehicle/edit', component: EditVehicleComponent},
  { path: 'vehicle/list', component: ListComponent }
  { path: 'user/list', component: UserListComponent }
];

@NgModule({
  exports: [ RouterModule ],
  imports: [ RouterModule.forRoot(routes) ]
})

export class AppRoutingModule {}