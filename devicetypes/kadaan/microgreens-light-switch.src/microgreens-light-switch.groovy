metadata {
    definition (name: "Microgreens Light Switch", namespace: "kadaan", author: "Joel Baranick") {
        capability "Actuator"
        capability "Refresh"
        capability "Sensor"
        capability "Switch"
        
        attribute "auto", "enum", ["on", "off"]
        
        command "auto"
        command "manual"
        command "on"
        command "off"
    }

    preferences {}

    tiles(scale: 2) {
        multiAttributeTile(name:"auto", type: "lighting", width: 3, height: 3, canChangeIcon: true){
            tileAttribute ("device.auto", key: "PRIMARY_CONTROL") {
                attributeState "on", label:'Auto', action:"manual", icon:"st.Lighting.light11", backgroundColor:"#8db738", defaultState: true
                attributeState "off", label:'Manual', action:"auto", icon:"st.Lighting.light11", backgroundColor:"#FFFFFF"
            }
        }
        
        multiAttributeTile(name:"switch", type: "lighting", width: 3, height: 3, canChangeIcon: true){
            tileAttribute ("device.switch", key: "PRIMARY_CONTROL") {
                attributeState "on", label:'${name}', action:"switch.off", icon:"st.Lighting.light11", backgroundColor:"#8db738", defaultState: true
                attributeState "off", label:'${name}', action:"switch.on", icon:"st.Lighting.light11", backgroundColor:"#FFFFFF"
            }
        }

        main(["auto"])
        details(["auto", "switch"])

    }
}

def installed() {
	initialize()
}

def updated() {
    initialize()
}

def initialize() {
   	state.auto = "on"
}

def parse(String description) {
}

def on() {
    sendEvent(name: "switch", value: "on", isStateChange: true)
}

def off() {
    sendEvent(name: "switch", value: "off", isStateChange: true)
}

def auto() {
    sendEvent(name: "auto", value: "on", isStateChange: true)
}

def manual() {
    sendEvent(name: "auto", value: "off", isStateChange: true)
}