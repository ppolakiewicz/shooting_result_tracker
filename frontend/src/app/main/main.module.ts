import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {DashboardComponent} from './dashboard/dashboard.component';
import {RouterModule, Routes} from "@angular/router";
import {MatIconModule} from "@angular/material/icon";
import {MatToolbarModule} from "@angular/material/toolbar";
import {MatButtonModule} from "@angular/material/button";
import {MatMenuModule} from "@angular/material/menu";
import {MatTooltipModule} from "@angular/material/tooltip";
import {MatSidenavModule} from "@angular/material/sidenav";
import {MatListModule} from "@angular/material/list";
import {FaIconComponent, FontAwesomeModule} from "@fortawesome/angular-fontawesome";
import {MainComponent} from "./main.component";
import {MagazineListComponent} from './magazinelist/magazine-list.component';

const routes: Routes = [
  {
    path: '', component: MainComponent, children: [
      {path: 'dashboard', component: DashboardComponent, title: 'Dashboard'},
      {
        path: 'magazine',
        loadChildren: () => import('../magazine/magazine.module').then(m => m.MagazineModule),
        title: 'Magazine'
      }
    ]
  },
]

@NgModule({
  declarations: [
    DashboardComponent,
    MainComponent,
    MagazineListComponent
  ],
  exports: [],
  imports: [
    RouterModule.forChild(routes),
    CommonModule,
    MatIconModule,
    MatToolbarModule,
    MatButtonModule,
    MatMenuModule,
    MatTooltipModule,
    MatSidenavModule,
    MatListModule,
    FaIconComponent,
    FontAwesomeModule
  ]
})
export class MainModule {
}
