import {Component} from '@angular/core';
import {CommonModule} from '@angular/common';
import {FaIconComponent} from "@fortawesome/angular-fontawesome";
import {MatButtonModule} from "@angular/material/button";
import {MatListModule} from "@angular/material/list";
import {MatSidenavModule} from "@angular/material/sidenav";
import {MatToolbarModule} from "@angular/material/toolbar";
import {MatTooltipModule} from "@angular/material/tooltip";
import {RouterLink, RouterOutlet} from "@angular/router";
import {faBars, faGun, faRightFromBracket, faWarehouse} from "@fortawesome/free-solid-svg-icons";
import {AuthenticationService} from "../authentication/authentication.service";
import {NavigationService} from "./navigation.service";

@Component({
  selector: 'app-main',
  standalone: true,
  imports: [CommonModule, FaIconComponent, MatButtonModule, MatListModule, MatSidenavModule, MatToolbarModule, MatTooltipModule, RouterLink, RouterOutlet],
  templateUrl: './main.component.html',
  styleUrl: './main.component.css'
})
export class MainComponent {

  protected gunIcon = faGun;
  protected menuIcon = faBars;
  protected logoutIcon = faRightFromBracket;
  protected warehouseIcon = faWarehouse;


  constructor(private readonly authenticationService: AuthenticationService,
              private readonly navigationService: NavigationService) {
  }

  protected logout() {
    this.authenticationService.logout().subscribe(() => this.navigationService.goToLoginPage());
  }
}
