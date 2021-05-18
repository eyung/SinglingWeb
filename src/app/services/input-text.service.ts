import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class InputTextService {

  constructor(private http: HttpClient) { }

  rootURL = 'http://localhost:8080/api';

  sendText(text: string) {
    return this.http.post(this.rootURL + '/text', text);
  }
}