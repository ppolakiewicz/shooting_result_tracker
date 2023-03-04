import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {DashboardComponent} from './dashboard/dashboard.component';
import {RouterModule, Routes} from "@angular/router";

const routes: Routes = [
  {path: 'dashboard', component: DashboardComponent, title: 'Dashboard'}
]

@NgModule({
  declarations: [
    DashboardComponent
  ],
  imports: [
    RouterModule.forChild(routes),
    CommonModule
  ]
})
export class MainModule {
}
