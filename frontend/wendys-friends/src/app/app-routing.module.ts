import { NgModule } from "@angular/core";
import { Routes, RouterModule } from "@angular/router";
import { OwnerComponent } from "./components/owner/owner.component";
import { HorseComponent } from "./components/horse/horse.component";
import { ListComponent } from "./components/list/list.component";

const routes: Routes = [
  { path: "", redirectTo: "owner", pathMatch: "full" },
  { path: "owner", component: OwnerComponent },
  { path: "horse", component: HorseComponent },
  { path: "list", component: ListComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {}
