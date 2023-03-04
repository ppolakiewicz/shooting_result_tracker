import {Injectable} from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class TokenStorageService {

  private readonly TOKEN_KEY = 'auth-token';

  clear(): void {
    window.localStorage.removeItem(this.TOKEN_KEY);
  }

  public saveToken(token: string): void {
    window.localStorage.removeItem(this.TOKEN_KEY);
    window.localStorage.setItem(this.TOKEN_KEY, token);
  }

  public getToken(): string | null {
    return window.localStorage.getItem(this.TOKEN_KEY);
  }
}
