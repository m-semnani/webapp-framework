import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ConfigService, Config } from '../service/config.service';

@Component({
  selector: 'app-config-update',
  templateUrl: './config-update.component.html',
  styleUrls: ['./config-update.component.css']
})
export class ConfigUpdateComponent implements OnInit {

  id: number;
  config: Config;
  public submitted: boolean = false;

  constructor(private route: ActivatedRoute,private router: Router,
    private configService: ConfigService) { }

  ngOnInit() {
    this.config = new Config();

    this.id = this.route.snapshot.params['id'];

    this.configService.getConfig(this.id)
      .subscribe(data => {
        console.log(data)
        this.config = data;
      }, error => console.log(error));
  }

  updateConfig() {
    this.configService.updateConfig(this.id, this.config)
      .subscribe(data => console.log(data), error => console.log(error));
    this.config = new Config();
    this.gotoList();
  }

  onSubmit() {
    this.updateConfig();
  }

  gotoList() {
    this.router.navigate(['/configs']);
  }
}

