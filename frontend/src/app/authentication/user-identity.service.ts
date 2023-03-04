import {Injectable} from '@angular/core';
import {TokenStorageService} from "./token-storage.service";
import {UserIdentity} from "./authentication.types";
import jwtDecode from "jwt-decode";

@Injectable({
  providedIn: 'root'
})
export class UserIdentityService {

  private identity: UserIdentity | undefined;

  constructor(private readonly tokenStorageService: TokenStorageService) {
  }

  userExists(): boolean {
    if (!this.identity) {
      this.identity = this.decodeToken(this.tokenStorageService.getToken())
    }

    console.log(this.identity?.exp);
    console.log(Date.now());
    return this.identity != null && this.identity.exp < Date.now();
  }

  save(token: string): void {
    this.tokenStorageService.saveToken(token);
    this.identity = this.decodeToken(token);
  }

  clear(): void {
    this.tokenStorageService.clear();
    this.identity = undefined;
  }


  private decodeToken(token: string | null): UserIdentity | undefined {
    if (!token) {
      return undefined;
    }

    return jwtDecode<UserIdentity>(token);
  }

}
