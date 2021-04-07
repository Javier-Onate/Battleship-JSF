var TorpilleurSelect = false;
                var PASelect = false;
                var CroiseurSelect = false;
                var CTSelect = false;
                var SSSelect = false;
                var bodyRect = document.body.getBoundingClientRect()
                
                var card = document.getElementById("restrictPanel")
                var bodyCard = card.getBoundingClientRect()
                
                console.log(bodyCard.bottom - bodyRect.bottom)
                console.log(bodyCard.left - bodyRect.left)
                
                var PA = document.getElementById("porte-avion");
                PA.addEventListener('mousedown', function () {
                    PASelect=true;
                    CroiseurSelect=false;
                    CTSelect=false;
                    SSSelect=false;
                    TorpilleurSelect=false;
                    console.log("Selected PA");                  

                })

                var Croiseur = document.getElementById("croiseur");
                Croiseur.addEventListener('mousedown', function () {
                	PASelect=false;
                    CroiseurSelect=true;
                    CTSelect=false;
                    SSSelect=false;
                    TorpilleurSelect=false;
                    console.log("Selected Croiseur");
                })

                var CT = document.getElementById("contre-torpilleur");
                CT.addEventListener('mousedown', function () {
                	PASelect=false;
                    CroiseurSelect=false;
                    CTSelect=true;
                    SSSelect=false;
                    TorpilleurSelect=false;
					console.log("Selected CT")
				})

				var SS = document.getElementById("sous-marin");
                SS.addEventListener('mousedown', function () {
                	PASelect=false;
                    CroiseurSelect=false;
                    CTSelect=false;
                    SSSelect=true;
                    TorpilleurSelect=false;
					console.log("Selected SS")
				})

				var Torpilleur = document.getElementById("torpilleur");
                Torpilleur.addEventListener('mousedown', function () {
                	PASelect=false;
                    CroiseurSelect=false;
                    CTSelect=false;
                    SSSelect=false;
                    TorpilleurSelect=true;
					console.log("Selected Torpilleur")
				})


				Torpilleur.addEventListener('mouseup', function () {
					TorpilleurSelect=false;
					console.log("deselected : " + CTSelect)
				})

				SS.addEventListener('mouseup', function () {
					SSSelect=false;
					console.log("deselected : " + CTSelect)
				})

				CT.addEventListener('mouseup', function () {
					CTSelect=false;
					console.log("deselected : " + CTSelect)
				})

				Croiseur.addEventListener('mouseup', function () {
					CroiseurSelect=false;
					console.log("deselected : " + CroiseurSelect )
				})

				PA.addEventListener('mouseup', function () {
					PASelect=false;
					console.log("deselected :" + PASelect)
				})

				window.addEventListener("keydown", event => {
					if (event.isComposing || event.keyCode === 32) {
						if(CTSelect == true){
							var width = CT.offsetWidth
							var height = CT.offsetHeight
							CT.style.height = width+'px'
							CT.style.width = height+'px'
							console.log(CT.offsetWidth)
							console.log(CT.offsetHeight)
						}
						if(PASelect == true){
							var width = PA.offsetWidth
							var height = PA.offsetHeight
							PA.style.height = width+'px'
							PA.style.width = height+'px'
							console.log(PA.offsetWidth)
							console.log(PA.offsetHeight)
						}
						if(CroiseurSelect == true){
							console.log(PA.getBoundingClientRect().top)
							var width = Croiseur.offsetWidth
							var height = Croiseur.offsetHeight
							Croiseur.style.height = width+'px'
							Croiseur.style.width = height+'px'
							console.log(PA.getBoundingClientRect().top)
							var e = PA.getBoundingClientRect().top - 180
							PA.style.top = e + "px"
							console.log("PA.style.top : " + PA.style.top + " " + "bounding : " + PA.getBoundingClientRect().top)
						}
						if(SSSelect == true){
							var width = SS.offsetWidth
							var height = SS.offsetHeight
							SS.style.height = width+'px'
							SS.style.width = height+'px'
							console.log(SS.offsetWidth)
							console.log(SS.offsetHeight)
						}
						if(TorpilleurSelect == true){
							var width = Torpilleur.offsetWidth
							var height = Torpilleur.offsetHeight
							Torpilleur.style.height = width+'px'
							Torpilleur.style.width = height+'px'
							console.log(Torpilleur.offsetWidth)
							console.log(Torpilleur.offsetHeight)
						}
				}
 
					});
                

                PrimeFaces.widget.Droppable.prototype.bindDropListener = function () {

                	  				
                                    var _self = this;

                                    this.cfg.drop = function (event, ui) {
                                        if (_self.cfg.onDrop) {
                                            _self.cfg.onDrop.call(_self, event, ui);
                                        }
                                        if (_self.cfg.behaviors) {
                                            var dropBehavior = _self.cfg.behaviors['drop'];

                                            if (dropBehavior) {
                                                var ext = {
                                                    params: [
                                                        {
                                                            name: _self.id + '_dragId',
                                                            value: ui.draggable.attr('id')
                                                        }, {
                                                            name: _self.id + '_dropId',
                                                            value: _self.cfg.target
                                                        }, {
                                                            name: ui.draggable.attr('id') + '_left',
                                                            value: ui.position.left
                                                        }, {
                                                            name: ui.draggable.attr('id') + '_top',
                                                            value: ui.position.top
                                                        }, {
                                                            name: ui.draggable.attr('id') + '_bot',
                                                            value: ui.position.bottom
                                                        }, {
                                                            name: ui.draggable.attr('id') + '_right',
                                                            value: ui.position.right
                                                        }
                                                    ]
                                                };
                                                console.log(ext);
                                                dropBehavior.call(_self, ext);
                                            }
                                        }
                                    };
                                }
                                