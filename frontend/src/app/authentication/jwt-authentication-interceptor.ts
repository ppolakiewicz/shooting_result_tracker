import {Injectable} from "@angular/core";
import {HttpEvent, HttpHandler, HttpInterceptor, HttpRequest} from "@angular/common/http";
import {TokenStorageService} from "./token-storage.service";
import {Observable} from "rxjs";


@Injectable()
export class JwtAuthenticationInterceptor implements HttpInterceptor {

  private readonly TOKEN_HEADER_KEY = 'Authorization';

  constructor(private token: TokenStorageService) {
  }

  intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    let authReq = request;
    const token = this.token.getToken();
    if (token != null) {
      authReq = request.clone({headers: request.headers.set(this.TOKEN_HEADER_KEY, `Bearer ${token}`)});
    }
    return next.handle(authReq);
  }
}
