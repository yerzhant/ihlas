/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kz.azan.ihlas.web.event;

import kz.azan.ihlas.model.Indigent;

/**
 *
 * @author yerzhan
 */
public class IndigentEvent {

    public Indigent indigent;

    public IndigentEvent(Indigent indigent) {
        this.indigent = indigent;
    }
}
