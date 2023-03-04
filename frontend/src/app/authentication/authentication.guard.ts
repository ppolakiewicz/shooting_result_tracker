import {Injectable} from '@angular/core';
import {ActivatedRouteSnapshot, CanActivateChild, Router, RouterStateSnapshot, UrlTree} from '@angular/router';
import {Observable, of} from 'rxjs';
import {UserIdentityService} from "./user-identity.service";

@Injectable({
  providedIn: 'root'
})
export class AuthenticationGuard implements CanActivateChild {


  constructor(private readonly router: Router,
              private readonly userIdentityService: UserIdentityService) {
  }

  canActivateChild(childRoute: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<boolean | UrlTree> {
    if (this.userIdentityService.userExists()) {
      return of(true);
    }
    const urlTree = this.router.parseUrl('');

    urlTree.queryParams = {redirectTo: state.url};
    return of(urlTree);
  }


}
