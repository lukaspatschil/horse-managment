import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {FormsModule} from '@angular/forms';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {HeaderComponent} from './components/header/header.component';
import {OwnerComponent} from './components/owner/owner.component';
import {HttpClientModule} from '@angular/common/http';
import { HorseComponent } from './components/horse/horse.component';
import { AddOwnerComponent } from './components/add-owner/add-owner.component';
import { AddHorseComponent } from './components/add-horse/add-horse.component';
import { ListHorseComponent } from './components/list-horse/list-horse.component';
import { ListOwnerComponent } from './components/list-owner/list-owner.component';

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    OwnerComponent,
    HorseComponent,
    AddOwnerComponent,
    AddHorseComponent,
    ListHorseComponent,
    ListOwnerComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {
}
