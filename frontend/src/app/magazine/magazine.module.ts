import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {RouterModule, Routes} from "@angular/router";
import {MagazineMainComponent} from "./magazine-main.component";


const routes: Routes = [
  {
    path: '', component: MagazineMainComponent, children: []
  },
]

@NgModule({
  declarations: [],
  imports: [
    CommonModule,
    RouterModule.forChild(routes),
  ]
})
export class MagazineModule {
}
