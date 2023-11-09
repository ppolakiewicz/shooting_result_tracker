import {Injectable} from '@angular/core';
import {Observable, of, shareReplay, tap} from "rxjs";
import {AuthenticationResponseDto, UserAuthenticateDto} from "./authentication.model";
import {HttpClient} from "@angular/common/http";
import {UserIdentityService} from "./user-identity.service";

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {

  private readonly baseUrl = '/api/v1/auth';

  constructor(private readonly httpClient: HttpClient,
              private readonly userIdentityService: UserIdentityService) {
  }

  public registerUser(credentials: UserAuthenticateDto) {
    const registerUrl = `${this.baseUrl}/register`;
    return this.httpClient.post(registerUrl, credentials)
      .pipe(shareReplay());
  }

  public loginUser(credentials: UserAuthenticateDto) {
    return this.httpClient.post<AuthenticationResponseDto>(this.baseUrl, credentials)
      .pipe(
        tap(authenticationResponse => this.userIdentityService.save(authenticationResponse.token)),
        shareReplay()
      )
  }

  public logout(): Observable<null> {
    return of(null).pipe(
      tap(() => this.userIdentityService.clear())
    );
  }
}
