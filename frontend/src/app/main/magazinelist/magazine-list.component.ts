import {Component, OnInit} from '@angular/core';
import {faPlus, faWarehouse} from "@fortawesome/free-solid-svg-icons";
import {MagazineDto} from "../../magazine/magazine.model";
import {ActivatedRoute} from "@angular/router";
import {MagazineService} from "../../magazine/magazine.service";
import {MatDialog} from "@angular/material/dialog";
import {MagazineCreateDialogComponent} from "./magazine-create-dialog/magazine-create-dialog.component";
import {switchMap} from "rxjs";

@Component({
  selector: 'app-magazine-list',
  templateUrl: './magazine-list.component.html',
  styleUrl: './magazine-list.component.css'
})
export class MagazineListComponent implements OnInit {

  protected warehouseIcon = faWarehouse;
  protected plusIcon = faPlus;
  protected magazines: MagazineDto[] = [];

  constructor(private readonly activatedRoute: ActivatedRoute,
              private readonly magazineService: MagazineService,
              private readonly dialog: MatDialog) {
  }

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(data => {
      this.magazines = data['magazines'];
    })
  }

  addMagazine() {
    const magazineCreateDialog = this.dialog.open(MagazineCreateDialogComponent);
    magazineCreateDialog.afterClosed().pipe(
      switchMap(newMagazineName => this.magazineService.create({name: newMagazineName}))
    ).subscribe(magazineDto => this.magazines.push(magazineDto));
  }
}
