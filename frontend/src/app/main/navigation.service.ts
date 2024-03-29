import {Injectable} from '@angular/core';
import {Router} from "@angular/router";

@Injectable({
  providedIn: 'root'
})
export class NavigationService {

  constructor(private readonly router: Router) {
  }

  public goToDashBoard(): void {
    this.router.navigateByUrl('/dashboard');
  }

  goToLoginPage() {
    this.router.navigateByUrl('');
  }
}
