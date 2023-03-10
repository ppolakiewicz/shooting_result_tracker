import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';

import {AppComponent} from './app.component';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {RouterModule, RouterOutlet, Routes} from "@angular/router";
import {HttpClientModule} from "@angular/common/http";
import {AuthenticationGuard} from "./authentication/authentication.guard";

const routes: Routes = [
  {path: '', pathMatch: 'full', loadChildren: () => import('./login/login.module').then(m => m.LoginModule)},
  {
    path: '',
    canActivateChild: [AuthenticationGuard],
    loadChildren: () => import('./main/main.module').then(m => m.MainModule)
  },
  {path: '**', redirectTo: ''}
]

@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    RouterModule.forRoot(routes),
    BrowserModule,
    BrowserAnimationsModule,
    RouterOutlet,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {
}
