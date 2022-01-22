import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class VehicleService {

  private baseUrl = 'http://localhost:8080/';
  private url = 'api/vehicles';

  constructor(
    private http: HttpClient
    ) { }

}
