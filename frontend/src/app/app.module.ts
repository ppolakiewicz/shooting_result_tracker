import {inject, NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';

import {AppComponent} from './app.component';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {ActivatedRouteSnapshot, RouterModule, RouterOutlet, RouterStateSnapshot, Routes} from "@angular/router";
import {HTTP_INTERCEPTORS, HttpClientModule} from "@angular/common/http";
import {AuthenticationGuard} from "./authentication/authentication.guard";
import {JwtAuthenticationInterceptor} from "./authentication/jwt-authentication-interceptor";
import {MagazineService} from "./magazine/magazine.service";

const routes: Routes = [
  {path: '', pathMatch: 'full', loadChildren: () => import('./login/login.module').then(m => m.LoginModule)},
  {
    path: '',
    canActivateChild: [(childRoute: ActivatedRouteSnapshot, state: RouterStateSnapshot) => inject(AuthenticationGuard).canActivateChild(childRoute, state)],
    loadChildren: () => import('./main/main.module').then(m => m.MainModule),
    resolve: {magazines: () => inject(MagazineService).getAll()}

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
  providers: [
    {provide: HTTP_INTERCEPTORS, useClass: JwtAuthenticationInterceptor, multi: true},
  ],
  bootstrap: [AppComponent]
})
export class AppModule {
}
