import {Component} from '@angular/core';
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {AuthenticationService} from "../authentication/authentication.service";
import {RoutingService} from "../routing/routing.service";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {

  readonly loginForm: FormGroup = new FormGroup({
    email: new FormControl<string>('', [Validators.required]),
    password: new FormControl<string>('', [Validators.required])
  });


  constructor(private readonly authorizationService: AuthenticationService,
              private readonly routingService: RoutingService) {
  }

  public login(): void {
    this.loginForm.markAsTouched();
    if (this.loginForm.valid) {
      this.authorizationService.loginUser(this.loginForm.value).subscribe(() => {
        this.routingService.goToDashBoard();
      })
    }
  }

  register() {
    this.loginForm.markAsTouched();
    if (this.loginForm.valid) {
      this.authorizationService.registerUser(this.loginForm.value).subscribe(response => {
        console.log(response);
      })
    }
  }

  logout() {
    this.authorizationService.logout().subscribe(() => console.log('Logged out'));
  }
}
