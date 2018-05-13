import { NgModule }             from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { VehicleComponent }      from './vehicle/vehicle.component';
import { ListComponent } from './vehicle/list/list.component';

const routes: Routes = [
  { path: 'vehicles', component: VehicleComponent },
  { path: 'vehicle/list', component: ListComponent }
];

@NgModule({
  exports: [ RouterModule ],
  imports: [ RouterModule.forRoot(routes) ]
})

export class AppRoutingModule {}