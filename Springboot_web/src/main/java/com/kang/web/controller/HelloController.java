package com.kang.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

	@GetMapping("hello")
	public String hello(Model model) {
		model.addAttribute("data", "Kang Byoung Kook!!");
		return "hello"; // viewResolver가 templates폴더 안 html 찾아보고 처리함.
		// spring boot devtools 의존성 추가 시 html 파일을 컴파일[저장]만 해주면 서버 재시작 없이 View 변경이 가능하다.
	}

	@GetMapping("hello_mvc")
	public String helloMvc(@RequestParam(value = "name", required = false) String name, Model model) {
		// required 디폴트 true null 전송 시 오류발생함.
		model.addAttribute("name", name);
		return "hello-template";
	}

	@GetMapping("hello_string")
	@ResponseBody // HTTP에서 바디부에 직접 반응을 넣어주겠다. 템플릿 뷰가 없이 바로 문자가 그대로 내려간다.
	public String helloString(@RequestParam(value = "name") String name) {
		return "hello " + name;
	}

	@GetMapping("hello_api") // Json key, value로 구성된 방식으로 리턴된다.
	@ResponseBody //그대로 html 바디부로 던지지만, 객체인 경우 기본 디폴트가 Json방식으로 만들어서 반환하는게 정책이다!
	public Hello helloApi(@RequestParam("name") String name) {
		// 모든 단축키 보기 : ctrl + shift + L
		// 새로만들기 : ctrl + n
		// 파일실행(run) : ctrl + F11
		// 자동import : ctrl + shift + O
		// 클래스 객체 자동생성 : alt + shift + S
		// 예외 : alt + shift + Z
		// 자동정렬 : ctrl shift + F
		Hello hello = new Hello();
		hello.setName(name);
		return hello; // 처음으로 객체를 리턴시킴.
	}

	static class Hello {
		private String name;

		// getter & setter 단축키 shift + alt + s > r
		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}
	}
}
