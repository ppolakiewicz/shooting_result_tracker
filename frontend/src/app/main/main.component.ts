import {Component} from '@angular/core';
import {faBars, faGun, faRightFromBracket} from "@fortawesome/free-solid-svg-icons";
import {AuthenticationService} from "../authentication/authentication.service";
import {NavigationService} from "./navigation.service";

@Component({
  selector: 'app-main',
  templateUrl: './main.component.html',
  styleUrl: './main.component.css'
})
export class MainComponent {

  protected gunIcon = faGun;
  protected menuIcon = faBars;
  protected logoutIcon = faRightFromBracket;

  constructor(private readonly authenticationService: AuthenticationService,
              private readonly navigationService: NavigationService) {
  }

  protected logout() {
    this.authenticationService.logout().subscribe(() => this.navigationService.goToLoginPage());
  }
}
