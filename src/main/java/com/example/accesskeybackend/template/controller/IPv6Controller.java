package com.example.accesskeybackend.template.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.*;

@RestController
@RequestMapping("/api/web/checkIpv6Support")
@AllArgsConstructor
public class IPv6Controller {

    @GetMapping("/{siteUrl}")
    public boolean checkIPv6Support(@PathVariable String siteUrl) {
        try {
            URI uri = new URI(siteUrl);
            String hostname = uri.getHost() != null ? uri.getHost() : siteUrl;
            InetAddress[] addresses = InetAddress.getAllByName(hostname);
            for (InetAddress address : addresses) {
                if (address instanceof Inet6Address) {
                    return true;
                }
            }
            return false;
        } catch (UnknownHostException | URISyntaxException e) {
            return false;
        }

    }

}
