import {Injectable} from '@angular/core';
import {Router} from "@angular/router";

@Injectable({
  providedIn: 'root'
})
export class RoutingService {

  constructor(private readonly router: Router) {
  }

  public goToDashBoard(): void {
    this.router.navigateByUrl('/dashboard');
  }
}
