import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {MagazineCreateCommand, MagazineDto} from "./magazine.model";

@Injectable({
  providedIn: 'root'
})
export class MagazineService {

  private readonly baseUrl = '/api/v1/magazine';

  constructor(private readonly httpClient: HttpClient) {
  }

  public getAll(): Observable<MagazineDto[]> {
    return this.httpClient.get<MagazineDto[]>(this.baseUrl);
  }

  public create(command: MagazineCreateCommand): Observable<MagazineDto> {
    return this.httpClient.post<MagazineDto>(this.baseUrl, command);
  }
}
