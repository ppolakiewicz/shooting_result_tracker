import {inject} from '@angular/core';
import {CanActivateFn, Router} from '@angular/router';
import {of} from 'rxjs';
import {UserIdentityService} from "./user-identity.service";

export const authenticationGuard: CanActivateFn = (route, state) => {
  const userIdentityService: UserIdentityService = inject(UserIdentityService);
  const router: Router = inject(Router);

  if (userIdentityService.userExists()) {
    return of(true);
  }
  const urlTree = router.parseUrl('');

  urlTree.queryParams = {redirectTo: state.url};
  return of(urlTree);
}
