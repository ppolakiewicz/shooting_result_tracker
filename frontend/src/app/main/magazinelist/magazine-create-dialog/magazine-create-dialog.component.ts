import {Component} from '@angular/core';
import {CommonModule} from '@angular/common';
import {MatDialogActions, MatDialogClose, MatDialogContent} from "@angular/material/dialog";
import {MatInputModule} from "@angular/material/input";
import {MatButtonModule} from "@angular/material/button";
import {FormControl, ReactiveFormsModule, Validators} from "@angular/forms";
import {DialogRef} from "@angular/cdk/dialog";

@Component({
  selector: 'app-magazine-create-dialog',
  standalone: true,
  imports: [CommonModule, MatDialogContent, MatInputModule, MatDialogActions, MatButtonModule, MatDialogClose, ReactiveFormsModule],
  templateUrl: './magazine-create-dialog.component.html',
  styleUrl: './magazine-create-dialog.component.css'
})
export class MagazineCreateDialogComponent {

  protected magazineNameControl: FormControl = new FormControl<string>('', [Validators.required]);

  constructor(private readonly dialogRef: DialogRef) {
  }

  cancel() {
    this.dialogRef.close();
  }
}
